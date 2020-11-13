package com.michaellaguerre.homeassignment.domain.interactors

import androidx.lifecycle.map
import androidx.paging.map
import com.michaellaguerre.homeassignment.data.repositories.CommentsRepository
import javax.inject.Inject

/**
 * Get post comments use case.
 *
 * This use case allows the user to retrieve a list of [Comment] for a given [Post].
 *
 * Data is first retrieved from network before being cached and served from the database.
 *
 * @param commentsRepository the repository in charge of handling [Comment]
 */
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