package com.michaellaguerre.symphony.domain.interactors

import com.michaellaguerre.symphony.core.interactors.UseCase
import com.michaellaguerre.symphony.data.repositories.AuthorsRepository
import com.michaellaguerre.symphony.domain.entities.Author
import javax.inject.Inject

class GetAuthors
@Inject constructor(private val authorsRepository: AuthorsRepository) : UseCase<List<Author>, UseCase.None>() {

    override suspend fun run(params: None) = authorsRepository.getAuthors()
}