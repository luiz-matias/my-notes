package com.luizmatias.mynotes.data.local.wrapper

import com.luizmatias.mynotes.data.local.model.NoteData
import com.luizmatias.mynotes.domain.entities.Note

class NoteWrapper {

    fun fromNoteDataToNote(noteData: NoteData): Note = Note(
        noteData.id,
        noteData.title,
        noteData.description,
        noteData.createdAt,
        noteData.updatedAt
    )

    fun fromNoteToNoteData(note: Note): NoteData = NoteData(
        note.id,
        note.title,
        note.description,
        note.createdAt,
        note.updatedAt
    )

}