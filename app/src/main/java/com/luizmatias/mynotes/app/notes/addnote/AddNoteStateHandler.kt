package com.luizmatias.mynotes.app.notes.addnote

sealed class AddNoteStateHandler {
    class setTitleError(val error: Boolean) : AddNoteStateHandler()
    class setDescriptionError(val error: Boolean) : AddNoteStateHandler()
    class noteAdded() : AddNoteStateHandler()
}