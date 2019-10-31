package com.luizmatias.mynotes.domain.entities

data class Note(
    val id: Int,
    var title: String,
    var description: String,
    var createdAt: Long,
    var updatedAt: Long? = null,
    var deletedAt: Long? = null
)