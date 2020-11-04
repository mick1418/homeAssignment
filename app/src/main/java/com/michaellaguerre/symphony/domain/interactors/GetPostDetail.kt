package com.michaellaguerre.symphony.domain.interactors

import com.michaellaguerre.symphony.core.interactors.UseCase
import com.michaellaguerre.symphony.data.repositories.PostsRepository
import com.michaellaguerre.symphony.domain.entities.Post
import javax.inject.Inject

class GetPostDetail
@Inject constructor(private val postsRepository: PostsRepository) :
    UseCase<Post, GetPostDetail.Params>() {

    override suspend fun run(params: Params) = postsRepository.getPostDetail(params.postId)

    data class Params(val postId: Int)
}