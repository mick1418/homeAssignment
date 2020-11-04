package com.michaellaguerre.symphony.data.repositories

import com.michaellaguerre.symphony.core.utils.Either
import com.michaellaguerre.symphony.core.utils.Failure
import com.michaellaguerre.symphony.domain.entities.Author
import com.michaellaguerre.symphony.domain.entities.Post

interface PostsRepository {
    fun getPostDetail(postId: Int): Either<Failure, Post>
    fun getPostForAuthor(authorId: Int): Either<Failure, List<Post>>
}