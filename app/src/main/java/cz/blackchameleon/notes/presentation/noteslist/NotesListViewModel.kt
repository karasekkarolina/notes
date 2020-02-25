package cz.blackchameleon.notes.presentation.noteslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.blackchameleon.notes.domain.Note
import cz.blackchameleon.notes.usecases.GetNotesList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotesListViewModel(
    private val getNotesList: GetNotesList
) : ViewModel() {

    private val _notes: MutableLiveData<List<Note>> = MutableLiveData()
    val notes: LiveData<List<Note>> = _notes

    init {
        loadNotes()
    }

    fun onRefresh() {
        loadNotes()
    }

    fun onNoteClick(note: Note) {

    }

    private fun loadNotes() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _notes.postValue(getNotesList())
            }
        }
    }
}