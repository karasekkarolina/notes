package cz.blackchameleon.notes.presentation.noteslist

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import cz.blackchameleon.notes.R
import cz.blackchameleon.notes.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_notes_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotesListFragment : BaseFragment(R.layout.fragment_notes_list) {
    private val viewModel: NotesListViewModel by viewModel()

    private val notesAdapter: NotesAdapter by lazy {
        NotesAdapter(viewModel::onNoteClick)
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

    }

    override fun setupObservers() {

    }

    override fun setupListeners() {
        swipe_refresh.setOnRefreshListener(viewModel::onRefresh)

        add_button.setOnClickListener {
            findNavController().navigate(NotesListFragmentDirections.actionNoteDetail())
        }

        viewModel.notes.observe(this, Observer { notes ->
            if (notes != null) {
                notesAdapter.submitList(notes)
            }
            swipe_refresh.isRefreshing = false
        })
    }
}