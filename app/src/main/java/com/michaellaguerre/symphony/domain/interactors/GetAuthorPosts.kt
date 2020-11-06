package com.michaellaguerre.symphony.domain.interactors

import androidx.lifecycle.map
import androidx.paging.map
import com.michaellaguerre.symphony.data.repositories.PostsRepository
import javax.inject.Inject

class GetAuthorPosts
@Inject constructor(private val postsRepository: PostsRepository) {

    fun invoke(params: Params) = postsRepository.getPostsForAuthors(params.authorId)
        .map { pagingData ->
            pagingData.map { postEntity ->
                postEntity.toPost()
            }
        }

    data class Params(val authorId: Int)
}