package com.luizmatias.mynotes.ui.notes.notes

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.luizmatias.mynotes.data.local.NotesDatabase
import com.luizmatias.mynotes.data.model.Note

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    private var notes: LiveData<List<Note>>? = null

    fun getNotes(): LiveData<List<Note>>? {
        if (notes == null)
            loadNotes()
        return notes
    }

    private fun loadNotes() {
        notes = NotesDatabase.getInstance(getApplication() as Context)?.noteDAO()?.getAll()
    }

    fun removeNote(note: Note) {
        NotesDatabase.getInstance(getApplication() as Context)?.noteDAO()?.removeNote(note)
    }

}