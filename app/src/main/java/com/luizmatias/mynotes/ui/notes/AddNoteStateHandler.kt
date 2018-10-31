package com.luizmatias.mynotes.ui.notes

sealed class AddNoteStateHandler {
    class setTitleError(val error: Boolean) : AddNoteStateHandler()
    class setDescriptionError(val error: Boolean) : AddNoteStateHandler()
    class noteAdded() : AddNoteStateHandler()
}