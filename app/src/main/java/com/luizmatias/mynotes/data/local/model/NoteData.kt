package com.luizmatias.mynotes.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.luizmatias.mynotes.domain.entities.Note

@Entity(tableName = "notes")
data class NoteData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var title: String,
    var description: String,
    var createdAt: Long,
    var updatedAt: Long? = null,
    var deletedAt: Long? = null
) {
    fun toNote(): Note {
        return Note(
            this.id,
            this.title,
            this.description,
            this.createdAt,
            this.deletedAt
        )
    }
}