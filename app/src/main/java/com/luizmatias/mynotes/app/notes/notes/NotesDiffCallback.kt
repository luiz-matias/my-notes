package com.luizmatias.mynotes.app.notes.notes

import androidx.recyclerview.widget.DiffUtil
import com.luizmatias.mynotes.data.local.model.NoteData
import java.util.*

class NotesDiffCallback(private val newNoteData: ArrayList<NoteData>, private val oldNoteData: ArrayList<NoteData>) : DiffUtil.Callback() {


    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newNoteData[newItemPosition].id == oldNoteData[oldItemPosition].id
    }

    override fun getOldListSize(): Int {
        return oldNoteData.size
    }

    override fun getNewListSize(): Int {
        return newNoteData.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newNoteData[newItemPosition].id == oldNoteData[oldItemPosition].id
                && newNoteData[newItemPosition].title == oldNoteData[oldItemPosition].title
                && newNoteData[newItemPosition].description == oldNoteData[oldItemPosition].description
    }
}