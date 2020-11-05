package com.michaellaguerre.symphony.data.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class AuthorWithPosts(
    @Embedded val author: AuthorEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "authorId"
    )
    val posts: List<PostEntity>
)

data class PostWithComments(
    @Embedded val post: PostEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "postId"
    )
    val comments: List<CommentEntity>
)