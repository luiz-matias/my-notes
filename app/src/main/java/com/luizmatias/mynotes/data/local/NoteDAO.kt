package com.luizmatias.mynotes.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.luizmatias.mynotes.data.model.Note

@Dao
interface NoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNote(note: Note)

    @Query("SELECT * FROM notes")
    fun getAll(): LiveData<List<Note>>

    @Query("SELECT * FROM notes WHERE notes.id = :id")
    fun getNote(id: Int): LiveData<Note>

    @Update
    fun updateNote(note: Note)

    @Delete
    fun removeNote(note: Note)

}