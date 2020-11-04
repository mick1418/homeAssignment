package com.michaellaguerre.symphony.data.network

import com.michaellaguerre.symphony.core.platform.NetworkAvailabilityChecker
import com.michaellaguerre.symphony.core.utils.Either
import com.michaellaguerre.symphony.core.utils.Failure
import com.michaellaguerre.symphony.data.network.entities.AuthorEntity
import com.michaellaguerre.symphony.data.network.entities.PostEntity
import com.michaellaguerre.symphony.data.network.services.AuthorsService
import com.michaellaguerre.symphony.data.repositories.AuthorsRepository
import com.michaellaguerre.symphony.domain.entities.Author
import javax.inject.Inject

class NetworkAuthorsRepository
@Inject constructor(
    private val service: AuthorsService,
    networkAvailabilityChecker: NetworkAvailabilityChecker
) : AuthorsRepository, NetworkRequestHandler(networkAvailabilityChecker) {

    override fun getAuthors(): Either<Failure, List<Author>> {
        return request(
            call = service.getAuthors(),
            transformation = { it.map { authorEntity -> authorEntity.toAuthor() } },
            defaultResult = emptyList()
        )
    }

    override fun getAuthorDetail(authorId: Int): Either<Failure, Author> {
        return request(
            call = service.getAuthor(authorId),
            transformation = { it -> it.toAuthor() },
            defaultResult = AuthorEntity.empty
        )
    }

}