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

/**
 * View model that provides information what to display in view represented by [NotesListFragment]
 *
 * @see ViewModel
 *
 * @param getNotesList
 * @param deleteNote
 * @param createNote
 * @param setOpenNote
 *
 * @author Karolina Klepackova <klepackova.karolina@email.cz>
 * @since ver 1.0
 */
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
    private var deletedItem: Note = Note(id = deletedIndex)

    init {
        loadNotes()
    }

    fun onRefresh() {
        loadNotes()
    }

    /**
     * Deletes note on given position and stores info necessary for its recovery
     *
     * @param position Specifies which item was deleted
     */
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

    /**
     * Recovers previously deleted note
     */
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

    /**
     * Stores currently opened note into locally stored variable
     *
     * @param note
     */
    fun onNoteClick(note: Note) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                setOpenNote(note)
            }
        }
    }

    /**
     * Sets default value into locally stored opened note variable
     */
    fun wipeOpenNote() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                setOpenNote(Note())
            }
        }
    }

    /**
     * Loads list of notes whose are shown in UI
     */
    private fun loadNotes() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                with(getNotesList()) {
                    when (this) {
                        is Result.Success<List<Note>> -> {
                            _notes.postValue(this.data)
                        }
                        else -> {
                        }
                    }
                }
            }
        }
    }
}