package com.michaellaguerre.symphony.data.repositories

import com.michaellaguerre.symphony.core.utils.Either
import com.michaellaguerre.symphony.core.utils.Failure
import com.michaellaguerre.symphony.domain.entities.Author

interface AuthorsRepository {
    fun getAuthors(): Either<Failure, List<Author>>
    fun getAuthorDetail(authorId: Int): Either<Failure, Author>
}