package com.michaellaguerre.symphony.domain.interactors

import com.michaellaguerre.symphony.core.interactors.UseCase
import com.michaellaguerre.symphony.data.repositories.CommentsRepository
import com.michaellaguerre.symphony.domain.entities.Comment
import javax.inject.Inject

class GetPostComments
@Inject constructor(private val commentsRepository: CommentsRepository) :
    UseCase<List<Comment>, GetPostComments.Params>() {

    override suspend fun run(params: Params) = commentsRepository.getCommentsForPost(params.postId)

    data class Params(val postId: Int)
}