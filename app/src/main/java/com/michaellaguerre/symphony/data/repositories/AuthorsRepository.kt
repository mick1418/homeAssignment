package com.michaellaguerre.symphony.data.repositories

import com.michaellaguerre.symphony.core.utils.Either
import com.michaellaguerre.symphony.core.utils.Failure
import com.michaellaguerre.symphony.domain.entities.Author

/**
 * Abstraction interface used to separate the domain from the actual database implementations
 * (Room database or Retrofit service).
 *
 * This repository is in charge of handling everything related to Author.
 */
interface AuthorsRepository {

    /**
     * Retrieve the list of [Author].
     *
     * @return an [Either] containing either a [Failure] if something wrong happened, or a List of [Author]
     */
    fun getAuthors(): Either<Failure, List<Author>>

    /**
     * Retrieve the details of a given [Author].
     *
     * @param authorId the id of the [Author] we want to fetch
     * @return an [Either] containing either a [Failure] if something wrong happened, or an [Author]
     */
    fun getAuthorDetail(authorId: Int): Either<Failure, Author>
}