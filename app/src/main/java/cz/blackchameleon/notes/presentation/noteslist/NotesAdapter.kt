package cz.blackchameleon.notes.presentation.noteslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cz.blackchameleon.notes.R
import cz.blackchameleon.notes.framework.Note
import kotlinx.android.synthetic.main.item_note.view.*

class NotesAdapter(private val onClick: (Note) -> Unit) :
    ListAdapter<Note, NoteViewHolder>(object : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Note, newItem: Note) = oldItem == newItem
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        holder.bind(note, onClick)
    }
}

class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(note: Note, listener: (Note) -> Unit) {
        itemView.title.text = note.title
        itemView.container.setOnClickListener { listener(note) }
    }
}