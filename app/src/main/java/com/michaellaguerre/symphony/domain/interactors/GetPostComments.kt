package com.michaellaguerre.symphony.domain.interactors

import androidx.lifecycle.map
import androidx.paging.map
import com.michaellaguerre.symphony.data.repositories.CommentsRepository
import javax.inject.Inject

class GetPostComments
@Inject constructor(private val commentsRepository: CommentsRepository) {

    fun invoke(params: Params) = commentsRepository.getCommentsForPost(params.postId)
        .map { pagingData ->
            pagingData.map { commentEntity ->
                commentEntity.toComment()
            }
        }

    data class Params(val postId: Int)
}