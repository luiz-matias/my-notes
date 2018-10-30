package com.luizmatias.mynotes.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luizmatias.mynotes.data.model.Note

class NotesViewModel : ViewModel() {

    private lateinit var note: MutableLiveData<List<Note>>

    fun getNotes(): LiveData<List<Note>> {
        if (!::note.isInitialized) {
            note = MutableLiveData()
            loadNotes()
        }
        return note
    }

    private fun loadNotes() {

    }

}