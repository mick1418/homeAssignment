package com.michaellaguerre.symphony.data.network.services

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
     * @return a Call containing the list of [com.michaellaguerre.symphony.data.network.entities.AuthorEntity]
     */
    fun getAuthors() = authorsApi.getAuthors()

    /**
     * Retrieve the detail of a given [com.michaellaguerre.symphony.data.network.entities.AuthorEntity].
     *
     * @param authorId the author's ID
     *
     * @return a Call containing the [com.michaellaguerre.symphony.data.network.entities.AuthorEntity]
     */
    fun getAuthor(authorId: Int) = authorsApi.getAuthor(authorId)

}