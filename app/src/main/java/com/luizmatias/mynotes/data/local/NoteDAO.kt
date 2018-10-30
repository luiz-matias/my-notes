package com.luizmatias.mynotes.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.luizmatias.mynotes.data.model.Note

@Dao
interface NoteDAO {

    @Query("SELECT * FROM notes")
    fun getAll(): LiveData<List<Note>>

}