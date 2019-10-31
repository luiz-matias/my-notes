package com.luizmatias.mynotes.app.notes.editnote

sealed class EditNoteStateHandler {
    class setTitleError(val error: Boolean) : EditNoteStateHandler()
    class setDescriptionError(val error: Boolean) : EditNoteStateHandler()
    class noteEdited() : EditNoteStateHandler()
}