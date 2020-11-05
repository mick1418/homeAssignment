package com.michaellaguerre.symphony.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey val id: Int,
    var date: String,
    var title: String,
    var body: String,
    var imageUrl: String,
    var authorId: Int
)