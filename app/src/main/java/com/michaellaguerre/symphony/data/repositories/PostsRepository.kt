package com.michaellaguerre.symphony.data.repositories

import com.michaellaguerre.symphony.core.utils.Either
import com.michaellaguerre.symphony.core.utils.Failure
import com.michaellaguerre.symphony.domain.entities.Author
import com.michaellaguerre.symphony.domain.entities.Post

/**
 * Abstraction interface used to separate the domain from the actual database implementations
 * (Room database or Retrofit service).
 *
 * This repository is in charge of handling everything related to Posts.
 */
interface PostsRepository {

    /**
     * Retrieve the details of a given [Post].
     *
     * @param postId the id of the [Post] we want to fetch
     * @return an [Either] containing either a [Failure] if something wrong happened, or a [Post]
     */
    fun getPostDetail(postId: Int): Either<Failure, Post>

    /**
     * Retrieve the list of [Post] for a given [Author].
     *
     * @param authorId the id of the [Author] whose [Post] we want to fetch
     * @return an [Either] containing either a [Failure] if something wrong happened, or a List of [Post]
     */
    fun getPostForAuthor(authorId: Int): Either<Failure, List<Post>>
}