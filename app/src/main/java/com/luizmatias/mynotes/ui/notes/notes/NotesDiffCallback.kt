package com.luizmatias.mynotes.ui.notes.notes

import androidx.recyclerview.widget.DiffUtil
import com.luizmatias.mynotes.data.model.Note
import java.util.*

class NotesDiffCallback(private val newNotes: ArrayList<Note>, private val oldNotes: ArrayList<Note>) : DiffUtil.Callback() {


    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newNotes[newItemPosition].id == oldNotes[oldItemPosition].id
    }

    override fun getOldListSize(): Int {
        return oldNotes.size
    }

    override fun getNewListSize(): Int {
        return newNotes.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newNotes[newItemPosition].id == oldNotes[oldItemPosition].id
                && newNotes[newItemPosition].title == oldNotes[oldItemPosition].title
                && newNotes[newItemPosition].description == oldNotes[oldItemPosition].description
    }
}