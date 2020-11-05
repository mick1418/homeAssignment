package com.michaellaguerre.symphony.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
data class CommentEntity(
    @PrimaryKey var id: Int,
    var date: String,
    var body: String,
    var userName: String,
    var email: String,
    var avatarUrl: String,
    var postId: Int
)