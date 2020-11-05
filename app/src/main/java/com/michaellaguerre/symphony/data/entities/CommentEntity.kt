package com.michaellaguerre.symphony.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.michaellaguerre.symphony.domain.entities.Comment

@Entity(tableName = "comments")
data class CommentEntity(
    @PrimaryKey var id: Int,
    var date: String,
    var body: String,
    var userName: String,
    var email: String,
    var avatarUrl: String,
    var postId: Int
) {

    /**
     * Conversion to domain entity.
     */
    fun toComment() = Comment(id, date, body, userName, email, avatarUrl, postId)

    companion object {
        val empty = CommentEntity(
            0, "", "", "",
            "", "", 0
        )
    }
}