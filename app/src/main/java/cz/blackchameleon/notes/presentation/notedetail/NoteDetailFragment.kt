package cz.blackchameleon.notes.presentation.notedetail

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import cz.blackchameleon.notes.R
import cz.blackchameleon.notes.extensions.closeSoftKeyboard
import cz.blackchameleon.notes.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_note_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Fragment that handles UI for note detail
 *
 * @see BaseFragment
 * @see MenuItem.setOnMenuItemClickListener
 *
 * @author Karolina Klepackova <klepackova.karolina@email.cz>
 * @since ver 1.0
 */
class NoteDetailFragment : BaseFragment(R.layout.fragment_note_detail),
    MenuItem.OnMenuItemClickListener {

    private val viewModel: NoteDetailViewModel by viewModel()

    override fun onPause() {
        viewModel.saveDraft(note_text.text.toString())
        super.onPause()
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.note_detail_menu, menu)
        menu.getItem(0).setOnMenuItemClickListener(this)
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_save -> {
                viewModel.onSaveClick(note_text.text.toString())
                return true
            }
        }
        return false
    }

    override fun onBackPressed(): Boolean {
        viewModel.onBackClick(note_text.text.toString())
        return true
    }

    override fun setupView() {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(detail_toolbar)
    }

    override fun setupListeners() {
        detail_toolbar.setNavigationOnClickListener {
            viewModel.onBackClick(note_text.text.toString())
        }
    }

    override fun setupObservers() {
        viewModel.openNote.observe(this, Observer { note ->
            note_text.setText(note.title)
        })

        viewModel.closeNote.observe(this, Observer { close ->
            if (close) {
                context?.closeSoftKeyboard(note_text)
                NavHostFragment.findNavController(this).navigateUp()
            }
        })

        viewModel.loading.observe(this, Observer { visible ->
            loading_overlay.isVisible = visible
        })

        // Shows confirmation dialog if the current text is different than the one stored in displayed note
        viewModel.showWarningDialog.observe(this, Observer { event ->
            context?.let { context ->
                AlertDialog.Builder(context, R.style.Widget_Notes_AlertDialog)
                    .setTitle(R.string.note_lost)
                    .setMessage(R.string.really_continue)
                    .setPositiveButton(android.R.string.yes) { _, _ -> event(true) }
                    .setNegativeButton(android.R.string.no) { _, _ -> event(false) }
                    .show()
            }
        })
    }
}