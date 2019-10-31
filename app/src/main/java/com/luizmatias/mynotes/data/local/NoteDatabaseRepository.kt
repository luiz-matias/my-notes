package com.luizmatias.mynotes.data.local

import android.content.Context
import com.luizmatias.mynotes.data.local.wrapper.NoteWrapper
import com.luizmatias.mynotes.domain.entities.Note
import com.luizmatias.mynotes.domain.repositories.NotesRepository

class NoteDatabaseRepository(private val context: Context) : NotesRepository {

    override fun addNote(note: Note) {
        NotesDatabase.getInstance(context)
            ?.noteDAO()
            ?.addNote(NoteWrapper().fromNoteToNoteData(note))
    }

    override fun editNote(note: Note) {
        NotesDatabase.getInstance(context)
            ?.noteDAO()
            ?.updateNote(NoteWrapper().fromNoteToNoteData(note))
    }

    override fun deleteNote(note: Note) {
        NotesDatabase.getInstance(context)
            ?.noteDAO()
            ?.removeNote(NoteWrapper().fromNoteToNoteData(note))
    }

    override fun listNotes() {
        NotesDatabase.getInstance(context)
            ?.noteDAO()
            ?.getAll2()
    }
}