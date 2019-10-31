package com.luizmatias.mynotes.app.notes.notes

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.luizmatias.mynotes.data.local.NotesDatabase
import com.luizmatias.mynotes.data.local.model.NoteData

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    private var notes: LiveData<List<NoteData>>? = null

    fun getNotes(): LiveData<List<NoteData>>? {
        if (notes == null)
            loadNotes()
        return notes
    }

    private fun loadNotes() {
        notes = NotesDatabase.getInstance(getApplication() as Context)?.noteDAO()?.getAll()
    }

    fun removeNote(noteData: NoteData) {
        NotesDatabase.getInstance(getApplication() as Context)?.noteDAO()?.removeNote(noteData)
    }

}