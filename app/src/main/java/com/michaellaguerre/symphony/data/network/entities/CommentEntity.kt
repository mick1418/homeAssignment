package com.michaellaguerre.symphony.data.network.entities

import com.michaellaguerre.symphony.domain.entities.Comment

data class CommentEntity(
    val id: Int,
    val date: String,
    val body: String,
    val userName: String,
    val email: String,
    val avatarUrl: String,
    val postId: Int
) {

    /**
     * Conversion to domain entity.
     */
    fun toComment() = Comment(id, date, body, userName, email, avatarUrl, postId)
}