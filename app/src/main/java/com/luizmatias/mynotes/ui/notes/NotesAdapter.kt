package com.luizmatias.mynotes.ui.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.luizmatias.mynotes.R
import com.luizmatias.mynotes.data.model.Note
import com.luizmatias.mynotes.utils.timestampToDate
import kotlinx.android.synthetic.main.note_item.view.*
import java.util.*

class NotesAdapter(private val context: Context) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    private val notes: ArrayList<Note> = ArrayList()

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
            Toast.makeText(context, "TODO edit button", Toast.LENGTH_SHORT).show()
        }

        holder.buttonRemove.setOnClickListener {
            Toast.makeText(context, "TODO remove button", Toast.LENGTH_SHORT).show()
        }

    }

    fun addItens(notes: ArrayList<Note>) {
        this.notes.addAll(notes)
        notifyItemRangeChanged(this.notes.size - notes.size, this.notes.size)
    }

    fun replaceItens(notes: ArrayList<Note>) {
        this.notes.clear()
        this.notes.addAll(notes)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textViewTitle: TextView = itemView.textViewTitle
        val textViewCreatedAt: TextView = itemView.textViewCreatedAt
        val textViewDescription: TextView = itemView.textViewDescription
        val buttonRemove: MaterialButton = itemView.buttonRemove
        val buttonEdit: MaterialButton = itemView.buttonEdit

    }

}