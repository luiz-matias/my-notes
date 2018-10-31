package com.luizmatias.mynotes.ui.notes

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.luizmatias.mynotes.data.local.NotesDatabase
import com.luizmatias.mynotes.data.model.Note
import com.luizmatias.mynotes.utils.SingleLiveEvent
import java.util.*

class AddNoteViewModel(application: Application) : AndroidViewModel(application) {

    val addNoteStateHandler = SingleLiveEvent<AddNoteStateHandler>()

    fun validateNote(title: String, description: String) {
        var error = false

        addNoteStateHandler.value = AddNoteStateHandler.setTitleError(false)
        addNoteStateHandler.value = AddNoteStateHandler.setDescriptionError(false)

        if (title.length !in 1..30) {
            addNoteStateHandler.value = AddNoteStateHandler.setTitleError(true)
            error = true
        }
        if (description.length !in 1..400) {
            addNoteStateHandler.value = AddNoteStateHandler.setDescriptionError(true)
            error = true
        }

        if (!error) {
            addNoteStateHandler.value = AddNoteStateHandler.setTitleError(false)
            addNoteStateHandler.value = AddNoteStateHandler.setDescriptionError(false)
            saveNote(title, description)
        }

    }

    private fun saveNote(title: String, description: String) {
        val createdAt = Calendar.getInstance().timeInMillis
        val note = Note(0, title, description, createdAt, createdAt, null)
        NotesDatabase.getInstance(getApplication() as Context)?.noteDAO()?.addNote(note)

        addNoteStateHandler.value = AddNoteStateHandler.noteAdded()
    }

}