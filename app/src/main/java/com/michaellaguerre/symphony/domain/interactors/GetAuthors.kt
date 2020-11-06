package com.michaellaguerre.symphony.domain.interactors

import androidx.lifecycle.map
import androidx.paging.map
import com.michaellaguerre.symphony.data.repositories.AuthorsRepository
import javax.inject.Inject

class GetAuthors
@Inject constructor(private val authorsRepository: AuthorsRepository) {

    fun invoke() = authorsRepository.getAuthors()
        .map { pagingData ->
            pagingData.map { authorEntity ->
                authorEntity.toAuthor()
            }
        }
}