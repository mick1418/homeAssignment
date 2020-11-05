package com.michaellaguerre.symphony.data.repositories

import com.michaellaguerre.symphony.core.platform.NetworkAvailabilityChecker
import com.michaellaguerre.symphony.core.utils.Either
import com.michaellaguerre.symphony.core.utils.Failure
import com.michaellaguerre.symphony.data.database.daos.AuthorDao
import com.michaellaguerre.symphony.data.entities.AuthorEntity
import com.michaellaguerre.symphony.data.network.services.AuthorsService
import com.michaellaguerre.symphony.domain.entities.Author
import javax.inject.Inject

class AuthorsRepository
@Inject constructor(
    private val service: AuthorsService,
    private val dao: AuthorDao,
    networkAvailabilityChecker: NetworkAvailabilityChecker
) : BaseRepository(networkAvailabilityChecker) {

    fun getAuthors(): Either<Failure, List<Author>> {
        return request(
            call = service.getAuthors(),
            transformation = {

                // Insert into database
                dao.insertAll(it)

                // Convert to domain entity
                it.map { authorEntity -> authorEntity.toAuthor() }
            },
            defaultResult = emptyList()
        )
    }

    fun getAuthorDetail(authorId: Int): Either<Failure, Author> {
        return request(
            call = service.getAuthor(authorId),
            transformation = { it -> it.toAuthor() },
            defaultResult = AuthorEntity.empty
        )
    }

}