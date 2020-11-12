package com.michaellaguerre.symphony.domain.interactors

import androidx.lifecycle.map
import androidx.paging.map
import com.michaellaguerre.symphony.data.repositories.AuthorsRepository
import javax.inject.Inject

/**
 * Get authors use case.
 *
 * This use case allows the user to retrieve a list of Authors.
 *
 * Data is first retrieved from network before being cached and served from the database.
 *
 * @param authorsRepository the repository in charge of handling [Author]
 */
class GetAuthors
@Inject constructor(private val authorsRepository: AuthorsRepository) {

    fun invoke() = authorsRepository.getAuthors()
        .map { pagingData ->
            pagingData.map { authorEntity ->
                authorEntity.toAuthor()
            }
        }
}