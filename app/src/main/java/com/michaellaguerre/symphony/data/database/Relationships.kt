package com.michaellaguerre.symphony.data.database

import androidx.room.Embedded
import androidx.room.Relation
import com.michaellaguerre.symphony.data.entities.AuthorEntity
import com.michaellaguerre.symphony.data.entities.CommentEntity
import com.michaellaguerre.symphony.data.entities.PostEntity


/**
 * A data class holding an author with all his posts
 */
data class AuthorWithPosts(
    @Embedded val author: AuthorEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "authorId"
    )
    val posts: List<PostEntity>
)


/**
 * A data class holding a post with all its comments
 */
data class PostWithComments(
    @Embedded val post: PostEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "postId"
    )
    val comments: List<CommentEntity>
)