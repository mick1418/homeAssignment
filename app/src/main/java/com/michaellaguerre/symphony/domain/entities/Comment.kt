package com.michaellaguerre.symphony.domain.entities

data class Comment(
    val id: Int,
    val date: String,
    val body: String,
    val userName: String,
    val email: String,
    val avatarUrl: String,
    val postId: Int
)