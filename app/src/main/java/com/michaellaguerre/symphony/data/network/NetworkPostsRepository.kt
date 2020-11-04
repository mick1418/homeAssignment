package com.michaellaguerre.symphony.data.network

import com.michaellaguerre.symphony.core.platform.NetworkAvailabilityChecker
import com.michaellaguerre.symphony.core.utils.Either
import com.michaellaguerre.symphony.core.utils.Failure
import com.michaellaguerre.symphony.data.network.entities.PostEntity
import com.michaellaguerre.symphony.data.network.services.AuthorsService
import com.michaellaguerre.symphony.data.network.services.PostsService
import com.michaellaguerre.symphony.data.repositories.AuthorsRepository
import com.michaellaguerre.symphony.data.repositories.PostsRepository
import com.michaellaguerre.symphony.domain.entities.Author
import com.michaellaguerre.symphony.domain.entities.Post
import javax.inject.Inject

class NetworkPostsRepository
@Inject constructor(
    private val service: PostsService,
    networkAvailabilityChecker: NetworkAvailabilityChecker
) : PostsRepository, NetworkRequestHandler(networkAvailabilityChecker) {


    override fun getPostDetail(postId: Int): Either<Failure, Post> {
        return request(
            call = service.getPost(postId),
            transformation = { it -> it.toPost() },
            defaultResult = PostEntity.empty
        )
    }

    override fun getPostForAuthor(authorId: Int): Either<Failure, List<Post>> {
        return request(
            call = service.getPostsFromAuthor(authorId),
            transformation = { it.map { postEntity -> postEntity.toPost() } },
            defaultResult = emptyList()
        )
    }

}