package com.luizmatias.mynotes.ui.notes.editnote

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.luizmatias.mynotes.data.local.NotesDatabase
import com.luizmatias.mynotes.data.model.Note
import com.luizmatias.mynotes.utils.SingleLiveEvent
import java.util.*

class EditNoteViewModel(application: Application) : AndroidViewModel(application) {

    val editNoteStateHandler = SingleLiveEvent<EditNoteStateHandler>()
    private var note: LiveData<Note>? = null

    public fun getNote(id: Int): LiveData<Note>? {
        if (note == null)
            loadNote(id)
        return note
    }

    fun validateNote(title: String, description: String) {
        var error = false

        editNoteStateHandler.value = EditNoteStateHandler.setTitleError(false)
        editNoteStateHandler.value = EditNoteStateHandler.setDescriptionError(false)

        if (title.length !in 1..30) {
            editNoteStateHandler.value = EditNoteStateHandler.setTitleError(true)
            error = true
        }
        if (description.length !in 1..400) {
            editNoteStateHandler.value = EditNoteStateHandler.setDescriptionError(true)
            error = true
        }

        if (!error) {
            editNoteStateHandler.value = EditNoteStateHandler.setTitleError(false)
            editNoteStateHandler.value = EditNoteStateHandler.setDescriptionError(false)
            val noteEdited = note?.value
            noteEdited?.title = title
            noteEdited?.description = description
            noteEdited?.updatedAt = Calendar.getInstance().timeInMillis

            editNote(noteEdited)
        }

    }

    fun editNote(note: Note?) {
        note?.let {
            NotesDatabase.getInstance(getApplication() as Context)?.noteDAO()?.updateNote(it)
            editNoteStateHandler.value = EditNoteStateHandler.noteEdited()
        }
    }

    private fun loadNote(id: Int) {
        note = NotesDatabase.getInstance(getApplication() as Context)?.noteDAO()?.getNote(id)
    }

}