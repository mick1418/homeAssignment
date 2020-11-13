package com.michaellaguerre.homeassignment.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.michaellaguerre.homeassignment.domain.entities.Post

@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey val id: Int,
    var date: String,
    var title: String,
    var body: String,
    var imageUrl: String,
    var authorId: Int
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