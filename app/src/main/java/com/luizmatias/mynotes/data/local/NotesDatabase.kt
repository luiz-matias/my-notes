package com.luizmatias.mynotes.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.luizmatias.mynotes.data.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun noteDAO(): NoteDAO

    companion object {
        private var instance: NotesDatabase? = null

        fun getInstance(context: Context): NotesDatabase? {
            if (instance == null) {
                synchronized(NotesDatabase::class) {
                    instance = Room.databaseBuilder(context.applicationContext,
                            NotesDatabase::class.java, "notes.db")
                            .build()
                }
            }
            return instance
        }
    }

}