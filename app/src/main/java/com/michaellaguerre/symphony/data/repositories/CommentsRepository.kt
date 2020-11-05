package com.michaellaguerre.symphony.data.repositories

import com.michaellaguerre.symphony.core.utils.Either
import com.michaellaguerre.symphony.core.utils.Failure
import com.michaellaguerre.symphony.domain.entities.Comment

/**
 * Abstraction interface used to separate the domain from the actual database implementations
 * (Room database or Retrofit service).
 *
 * This repository is in charge of handling everything related to Comments.
 */
interface CommentsRepository {

    /**
     * Retrieve the list of [Comment] for a given [Post].
     *
     * @param postId the id of the [Post] whose [Comment] we want to fetch
     * @return an [Either] containing either a [Failure] if something wrong happened, or a List of [Comment]
     */
    fun getCommentsForPost(postId: Int): Either<Failure, List<Comment>>
}