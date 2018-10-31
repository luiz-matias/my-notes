package com.luizmatias.mynotes.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
        @PrimaryKey(autoGenerate = true) val id: Int,
        var title: String,
        var description: String,
        var createdAt: Long,
        var updatedAt: Long? = null,
        var deletedAt: Long? = null
)