package com.michaellaguerre.symphony.data.repositories

import com.michaellaguerre.symphony.core.utils.Either
import com.michaellaguerre.symphony.core.utils.Failure
import com.michaellaguerre.symphony.domain.entities.Comment

interface CommentsRepository {
    fun getCommentsForPost(postId: Int): Either<Failure, List<Comment>>
}