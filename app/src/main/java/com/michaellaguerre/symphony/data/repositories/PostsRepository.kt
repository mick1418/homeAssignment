package com.michaellaguerre.symphony.data.repositories

import com.michaellaguerre.symphony.core.platform.NetworkAvailabilityChecker
import com.michaellaguerre.symphony.core.utils.Either
import com.michaellaguerre.symphony.core.utils.Failure
import com.michaellaguerre.symphony.data.database.daos.PostDao
import com.michaellaguerre.symphony.data.entities.PostEntity
import com.michaellaguerre.symphony.data.network.services.PostsService
import com.michaellaguerre.symphony.domain.entities.Post
import javax.inject.Inject

class PostsRepository
@Inject constructor(
    private val service: PostsService,
    private val dao: PostDao,
    networkAvailabilityChecker: NetworkAvailabilityChecker
) : BaseRepository(networkAvailabilityChecker) {


    fun getPostDetail(postId: Int): Either<Failure, Post> {
        return request(
            call = service.getPost(postId),
            transformation = {
                // Insert into database
                dao.insert(it)

                // Convert to domain entity
                it.toPost()
            },
            defaultResult = PostEntity.empty
        )
    }

    fun getPostForAuthor(authorId: Int): Either<Failure, List<Post>> {
        return request(
            call = service.getPostsFromAuthor(authorId),
            transformation = {

                // Insert into database
                dao.insertAll(it)

                // Convert to domain entity
                it.map { postEntity -> postEntity.toPost() }
            },
            defaultResult = emptyList()
        )
    }

}