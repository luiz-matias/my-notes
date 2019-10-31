package com.luizmatias.mynotes.app.notes.editnote

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.luizmatias.mynotes.data.local.NotesDatabase
import com.luizmatias.mynotes.data.local.model.NoteData
import com.luizmatias.mynotes.utils.SingleLiveEvent
import java.util.*

class EditNoteViewModel(application: Application) : AndroidViewModel(application) {

    val editNoteStateHandler = SingleLiveEvent<EditNoteStateHandler>()
    private var noteData: LiveData<NoteData>? = null

    public fun getNote(id: Int): LiveData<NoteData>? {
        if (noteData == null)
            loadNote(id)
        return noteData
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
            val noteEdited = noteData?.value
            noteEdited?.title = title
            noteEdited?.description = description
            noteEdited?.updatedAt = Calendar.getInstance().timeInMillis

            editNote(noteEdited)
        }

    }

    fun editNote(noteData: NoteData?) {
        noteData?.let {
            NotesDatabase.getInstance(getApplication() as Context)?.noteDAO()?.updateNote(it)
            editNoteStateHandler.value = EditNoteStateHandler.noteEdited()
        }
    }

    private fun loadNote(id: Int) {
        noteData = NotesDatabase.getInstance(getApplication() as Context)?.noteDAO()?.getNote(id)
    }

}