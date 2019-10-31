package com.luizmatias.mynotes.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.luizmatias.mynotes.data.local.model.NoteData

@Dao
interface NoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNote(noteData: NoteData)

    @Query("SELECT * FROM notes")
    fun getAll(): LiveData<List<NoteData>>

    @Query("SELECT * FROM notes")
    fun getAll2(): List<NoteData>

    @Query("SELECT * FROM notes WHERE notes.id = :id")
    fun getNote(id: Int): LiveData<NoteData>

    @Update
    fun updateNote(noteData: NoteData)

    @Delete
    fun removeNote(noteData: NoteData)

}