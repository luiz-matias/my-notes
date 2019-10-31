package com.luizmatias.mynotes.app.notes.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.luizmatias.mynotes.R
import com.luizmatias.mynotes.data.local.model.NoteData
import com.luizmatias.mynotes.utils.timestampToDate
import kotlinx.android.synthetic.main.note_item.view.*
import java.util.*


class NotesAdapter(private val context: Context) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    private val noteData: ArrayList<NoteData> = ArrayList()
    var onRemoveClickListener: (noteData: NoteData) -> Unit = { _ -> }
    var onEditClickListener: (noteData: NoteData) -> Unit = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = noteData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = noteData[position]

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

    fun updateItens(noteData: ArrayList<NoteData>) {
        val diffResult = DiffUtil.calculateDiff(NotesDiffCallback(noteData, this.noteData))
        this.noteData.clear()
        this.noteData.addAll(noteData)
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