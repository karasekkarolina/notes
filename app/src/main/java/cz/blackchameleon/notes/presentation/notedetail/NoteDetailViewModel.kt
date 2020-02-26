package cz.blackchameleon.notes.presentation.notedetail

import androidx.lifecycle.ViewModel

class NoteDetailViewModel(

) : ViewModel() {

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

    private fun startLoading() {
        loading.postValue(true)
    }

    private fun stopLoading() {
        loading.postValue(false)
    }

    private fun closeDetail() {
        closeNote.postValue(Event(Unit))
    }

    private fun showConfirmationDialog(callback: (Boolean) -> Unit) {
        showConfirmDialog.postValue(Event(callback))
    }
}