package com.michaellaguerre.symphony.data.network.services

import com.michaellaguerre.symphony.core.dependencies.Constants.Api.DEFAULT_PAGE_NUMBER
import com.michaellaguerre.symphony.core.dependencies.Constants.Api.DEFAULT_AUTHORS_PAGE_SIZE
import com.michaellaguerre.symphony.data.network.api.AuthorsApi
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthorsService
@Inject constructor(retrofit: Retrofit) {

    private val authorsApi by lazy { retrofit.create(AuthorsApi::class.java) }

    /**
     * Retrieve the list of [com.michaellaguerre.symphony.data.network.entities.AuthorEntity].
     *
     * The list is ordered alphabetically
     *
     * @return a List of [com.michaellaguerre.symphony.data.network.entities.AuthorEntity]
     */
    suspend fun getAuthors(pageNumber: Int = DEFAULT_PAGE_NUMBER, limit: Int = DEFAULT_AUTHORS_PAGE_SIZE) = authorsApi.getAuthors(pageNumber, limit)

    /**
     * Retrieve the detail of a given [com.michaellaguerre.symphony.data.network.entities.AuthorEntity].
     *
     * @param authorId the author's ID
     *
     * @return an [com.michaellaguerre.symphony.data.network.entities.AuthorEntity]
     */
    suspend fun getAuthor(authorId: Int) = authorsApi.getAuthor(authorId)

}