package com.michaellaguerre.symphony.data.network.entities

data class CommentEntity(
    val id: Int,
    val date: String,
    val body: String,
    val userName: String,
    val email: String,
    val avatarUrl: String,
    val postId: Int
)