package cz.blackchameleon.notes.presentation.notedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.blackchameleon.notes.framework.model.Note
import cz.blackchameleon.notes.usecases.*
import kotlinx.coroutines.launch

/**
 * View model that provides information what to display in view represented by [NoteDetailFragment]
 *
 * @see ViewModel
 *
 * @param getOpenNote
 * @param editNote
 * @param createNote
 *
 * @author Karolina Klepackova <klepackova.karolina@email.cz>
 * @since ver 1.0
 */
class NoteDetailViewModel(
    private val getOpenNote: GetOpenNote,
    private val setOpenNote: SetOpenNote,
    private val editNote: EditNote,
    private val createNote: CreateNote,
    private val getDraftNote: GetDraftNote,
    private val setDraftNote: SetDraftNote
) : ViewModel() {

    private val _openNote: MutableLiveData<Note> = MutableLiveData()
    val openNote: LiveData<Note> = _openNote

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    private val _closeNote: MutableLiveData<Boolean> = MutableLiveData()
    val closeNote: LiveData<Boolean> = _closeNote

    private val _showWarningDialog: MutableLiveData<(Boolean) -> Unit> = MutableLiveData()
    val showWarningDialog: LiveData<(Boolean) -> Unit> = _showWarningDialog

    init {
        startLoading()
        viewModelScope.launch {
            // Loads open note from locally stored variable
            _openNote.value = getDraftNote() ?: getOpenNote() ?: Note()
            stopLoading()
        }
    }

    // Checks if current note text is different from the stored one
    fun onBackClick(currentText: String) {
        if (currentText == _openNote.value?.title) {
            closeDetail()
        } else {
            // Provides the response which user selected in the dialog
            provideDialogAnswer { confirmed ->
                if (confirmed) {
                    closeDetail()
                }
            }
        }
    }

    // Saves note depending on if it was a new note or edited one
    fun onSaveClick(string: String) {
        viewModelScope.launch {
            _openNote.value?.let {
                if (it == Note()) {
                    createNote(Note(title = string))
                    setOpenNote(Note(title = string))
                } else {
                    editNote(Note(it.id, string))
                    setOpenNote(Note(it.id, string))
                }
                setDraftNote(null)
            }
            closeDetail()
        }
    }

    // Saves draft note when app goes to background
    fun saveDraft(text: String) {
        viewModelScope.launch {
            _openNote.value?.id?.let { id ->
                val note = Note(id, text)
                if (getOpenNote() != note) {
                    setDraftNote(note)
                }
                _openNote.value = note
            }
        }
    }

    private fun startLoading() {
        _loading.value = true
    }

    private fun stopLoading() {
        _loading.value = false
    }

    private fun closeDetail() {
        _closeNote.value = true
    }

    // Returns answer given in the dialog via callback
    private fun provideDialogAnswer(callback: (Boolean) -> Unit) {
        _showWarningDialog.value = callback
    }
}