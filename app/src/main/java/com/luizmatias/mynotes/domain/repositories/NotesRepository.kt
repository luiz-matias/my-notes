package com.luizmatias.mynotes.domain.repositories

import com.luizmatias.mynotes.domain.entities.Note

interface NotesRepository {

    fun addNote(note: Note)
    fun editNote(note: Note)
    fun deleteNote(note: Note)
    fun listNotes()

}