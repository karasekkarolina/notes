package cz.blackchameleon.notes.presentation.noteslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.blackchameleon.notes.framework.model.Note
import cz.blackchameleon.notes.framework.model.Result
import cz.blackchameleon.notes.usecases.CreateNote
import cz.blackchameleon.notes.usecases.DeleteNote
import cz.blackchameleon.notes.usecases.GetNotesList
import cz.blackchameleon.notes.usecases.SetOpenNote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotesListViewModel(
    private val getNotesList: GetNotesList,
    private val deleteNote: DeleteNote,
    private val createNote: CreateNote,
    private val setOpenNote: SetOpenNote
) : ViewModel() {

    private val _notes: MutableLiveData<List<Note>> = MutableLiveData()
    val notes: LiveData<List<Note>> = _notes

    private val _showRestoreDialog: MutableLiveData<Boolean> = MutableLiveData()
    val showRestoreDialog: LiveData<Boolean> = _showRestoreDialog

    private var deletedIndex: Int = -1
    private var deletedItem: Note =
        Note(id = deletedIndex)

    init {
        loadNotes()
    }

    fun onRefresh() {
        loadNotes()
    }

    fun onItemDeleted(position: Int) {
        _notes.value?.let {
            deletedItem = it[position]
            deletedIndex = position
            _notes.value = it.minus(deletedItem)
            _showRestoreDialog.value = true

            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    deleteNote(deletedItem)
                }
            }
        }
    }

    fun onRestoreClick() {
        _notes.value?.let {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    createNote(deletedItem)
                }
            }

            _notes.value = it.toMutableList().apply { add(deletedIndex, deletedItem) }
            _showRestoreDialog.value = false
            deletedItem = Note(id = -1)
            deletedIndex = -1
        }
    }

    fun onNoteClick(note: Note) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                setOpenNote(note)
            }
        }
    }

    fun onAddClick() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                setOpenNote(Note())
            }
        }
    }

    private fun loadNotes() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                with(getNotesList()) {
                    when (this) {
                        is Result.Success<List<Note>> -> { _notes.postValue(this.data) }
                        else -> { }
                    }
                }
            }
        }
    }
}