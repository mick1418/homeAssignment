package com.michaellaguerre.symphony.domain.interactors

import androidx.lifecycle.map
import androidx.paging.map
import com.michaellaguerre.symphony.data.repositories.PostsRepository
import javax.inject.Inject

/**
 * Get author posts use case.
 *
 * This use case allows the user to retrieve a list of [Post] for a given [Author].
 *
 * Data is first retrieved from network before being cached and served from the database.
 *
 * @param postsRepository the repository in charge of handling [Post]
 */
class GetAuthorPosts
@Inject constructor(private val postsRepository: PostsRepository) {

    fun invoke(params: Params) = postsRepository.getPostsForAuthor(params.authorId)
        .map { pagingData ->
            pagingData.map { postEntity ->
                postEntity.toPost()
            }
        }

    data class Params(val authorId: Int)
}