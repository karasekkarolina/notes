package cz.blackchameleon.notes.presentation.notedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.blackchameleon.notes.framework.model.Note
import cz.blackchameleon.notes.usecases.CreateNote
import cz.blackchameleon.notes.usecases.EditNote
import cz.blackchameleon.notes.usecases.GetOpenNote
import kotlinx.coroutines.launch

class NoteDetailViewModel(
    private val getOpenNote: GetOpenNote,
    private val editNote: EditNote,
    private val createNote: CreateNote
) : ViewModel() {

    private val _openNote: MutableLiveData<Note> = MutableLiveData()
    val openNote: LiveData<Note> = _openNote

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    private val _closeNote: MutableLiveData<Boolean> = MutableLiveData()
    val closeNote: LiveData<Boolean> = _closeNote

    private val _showConfirmDialog: MutableLiveData<(Boolean) -> Unit> = MutableLiveData()
    val showConfirmDialog: LiveData<(Boolean) -> Unit> = _showConfirmDialog

    private var noteChanged: Boolean = false

    init {
        startLoading()
        viewModelScope.launch {
            _openNote.value = getOpenNote() ?: Note()
            stopLoading()
        }
    }

    fun onBackClick() {
        if (!noteChanged) {
            closeDetail()
        } else {
            showConfirmationDialog { confirmed ->
                if (confirmed) {
                    closeDetail()
                }
            }
        }
    }

    fun onSaveClick(string: String) {
        viewModelScope.launch {
            // todo
            _openNote.value?.let {
                if (it == Note()) {
                    createNote(Note(title = string))
                } else {
                    editNote(Note(it.id, string))
                }
            }
            closeDetail()
        }
    }
/*
    fun onNoteChanged(text: String) {
        if (text != _openNote.value?.title) {
            noteChanged = true
        }
        _openNote.value?.title = text
    }
*/
    private fun startLoading() {
        _loading.value = true
    }

    private fun stopLoading() {
        _loading.value = false
    }

    private fun closeDetail() {
        _closeNote.value = true
    }

    private fun showConfirmationDialog(callback: (Boolean) -> Unit) {
        _showConfirmDialog.value = callback
    }
}