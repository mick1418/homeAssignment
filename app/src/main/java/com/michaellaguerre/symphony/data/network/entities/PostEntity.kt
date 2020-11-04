package com.michaellaguerre.symphony.data.network.entities

import com.michaellaguerre.symphony.domain.entities.Post

data class PostEntity(
    val id: Int,
    val date: String,
    val title: String,
    val body: String,
    val imageUrl: String,
    val authorId: Int
) {

    /**
     * Conversion to domain entity.
     */
    fun toPost() = Post(id, date, title, body, imageUrl, authorId)

    companion object {
        val empty = PostEntity(
            0, "", "", "",
            "", 0
        )
    }
}