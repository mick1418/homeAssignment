package com.michaellaguerre.symphony.domain.interactors

import com.michaellaguerre.symphony.core.interactors.UseCase
import com.michaellaguerre.symphony.data.repositories.PostsRepository
import com.michaellaguerre.symphony.domain.entities.Post
import javax.inject.Inject

class GetAuthorPosts
@Inject constructor(private val postsRepository: PostsRepository) :
    UseCase<List<Post>, GetAuthorPosts.Params>() {

    override suspend fun run(params: Params) = postsRepository.getPostForAuthor(params.authorId)

    data class Params(val authorId: Int)
}