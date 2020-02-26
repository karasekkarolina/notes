package cz.blackchameleon.notes.presentation.noteslist

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import cz.blackchameleon.notes.R
import cz.blackchameleon.notes.presentation.base.BaseFragment
import cz.blackchameleon.notes.presentation.base.SwipeToDeleteCallback
import kotlinx.android.synthetic.main.fragment_notes_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Fragment that handles UI for notes list
 *
 * @see BaseFragment
 *
 * @author Karolina Klepackova <klepackova.karolina@email.cz>
 * @since ver 1.0
 */
class NotesListFragment : BaseFragment(R.layout.fragment_notes_list) {

    private val viewModel: NotesListViewModel by viewModel()

    private val notesAdapter: NotesAdapter by lazy {
        NotesAdapter { note ->
            viewModel.onNoteClick(note)
            findNavController().navigate(NotesListFragmentDirections.actionNoteDetail())
        }
    }

    override fun setupView() {
        val layoutManager = LinearLayoutManager(context)
        notes_list.adapter = notesAdapter
        notes_list.layoutManager = layoutManager
        notes_list.addItemDecoration(
            DividerItemDecoration(
                notes_list.context,
                layoutManager.orientation
            )
        )
        notes_list.isNestedScrollingEnabled = true

        // Handler is used for possibility to restore currently deleted note
        val swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (viewHolder is NoteViewHolder) {
                    viewModel.onItemDeleted(viewHolder.adapterPosition)
                }
            }
        }

        // Helper which provides possibility to make swipe to dismiss RecyclerView's items
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(notes_list)
    }

    override fun setupObservers() {
        viewModel.notes.observe(this, Observer { notes ->
            if (notes != null) {
                notesAdapter.submitList(notes)
            }
            swipe_refresh.isRefreshing = false
        })

        viewModel.showRestoreDialog.observe(this, Observer {
            if (it) {
                val snackbar = Snackbar.make(notes_list, R.string.note_delete, Snackbar.LENGTH_SHORT)
                snackbar.setAction(R.string.undo_selection) { viewModel.onRestoreClick() }
                snackbar.setActionTextColor(resources.getColor(R.color.color_primary))
                snackbar.show()
            }
        })
    }

    override fun setupListeners() {
        swipe_refresh.setOnRefreshListener(viewModel::onRefresh)

        add_button.setOnClickListener {
            viewModel.wipeOpenNote()
            findNavController().navigate(NotesListFragmentDirections.actionNoteDetail())
        }
    }
}