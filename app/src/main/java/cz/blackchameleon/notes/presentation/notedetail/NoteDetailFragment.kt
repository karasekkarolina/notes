package cz.blackchameleon.notes.presentation.notedetail

import androidx.fragment.app.Fragment
import cz.blackchameleon.notes.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoteDetailFragment : Fragment(R.layout.fragment_note_detail) {
    val viewModel: NoteDetailViewModel by viewModel()


}