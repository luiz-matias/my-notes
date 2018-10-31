package com.luizmatias.mynotes.ui.notes.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.luizmatias.mynotes.R
import com.luizmatias.mynotes.data.model.Note
import com.luizmatias.mynotes.utils.timestampToDate
import kotlinx.android.synthetic.main.note_item.view.*
import java.util.*


class NotesAdapter(private val context: Context) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    private val notes: ArrayList<Note> = ArrayList()
    var onRemoveClickListener: (note: Note) -> Unit = { _ -> }
    var onEditClickListener: (note: Note) -> Unit = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]

        holder.textViewTitle.text = note.title
        holder.textViewCreatedAt.text = String.format(Locale.getDefault(),
                context.getString(R.string.created_at),
                timestampToDate(note.createdAt))

        holder.textViewDescription.text = note.description

        holder.buttonEdit.setOnClickListener {
            onEditClickListener(note)
        }

        holder.buttonRemove.setOnClickListener {
            onRemoveClickListener(note)
        }

    }

    fun updateItens(notes: ArrayList<Note>) {
        val diffResult = DiffUtil.calculateDiff(NotesDiffCallback(notes, this.notes))
        this.notes.clear()
        this.notes.addAll(notes)
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textViewTitle: TextView = itemView.textViewTitle
        val textViewCreatedAt: TextView = itemView.textViewCreatedAt
        val textViewDescription: TextView = itemView.textViewDescription
        val buttonRemove: MaterialButton = itemView.buttonRemove
        val buttonEdit: MaterialButton = itemView.buttonEdit

    }

}