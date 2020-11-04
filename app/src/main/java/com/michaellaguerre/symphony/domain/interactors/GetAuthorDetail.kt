package com.michaellaguerre.symphony.domain.interactors

import com.michaellaguerre.symphony.core.interactors.UseCase
import com.michaellaguerre.symphony.data.repositories.AuthorsRepository
import com.michaellaguerre.symphony.domain.entities.Author
import javax.inject.Inject

class GetAuthorDetail
@Inject constructor(private val authorsRepository: AuthorsRepository) :
    UseCase<Author, GetAuthorDetail.Params>() {

    override suspend fun run(params: Params) = authorsRepository.getAuthorDetail(params.authorId)

    data class Params(val authorId: Int)
}