package com.michaellaguerre.symphony.domain.entities

data class Post(
    val id: Int,
    val date: String,
    val title: String,
    val body: String,
    val imageUrl: String,
    val authorId: Int
)