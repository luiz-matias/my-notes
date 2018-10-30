package com.luizmatias.mynotes.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
        @PrimaryKey(autoGenerate = true) val id: Int,
        val title: String,
        val description: String,
        val createdAt: Long,
        var updatedAt: Long? = null,
        var deletedAt: Long? = null
)