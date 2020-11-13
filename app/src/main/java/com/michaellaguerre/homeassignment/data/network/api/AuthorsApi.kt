package com.michaellaguerre.homeassignment.data.network.api

import com.michaellaguerre.homeassignment.core.dependencies.Constants.Api.DEFAULT_AUTHORS_PAGE_SIZE
import com.michaellaguerre.homeassignment.core.dependencies.Constants.Api.DEFAULT_PAGE_NUMBER
import com.michaellaguerre.homeassignment.data.entities.AuthorEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit service interface containing author-related network calls
 */
interface AuthorsApi {

    @GET("authors?_sort=name&_order=asc")
    suspend fun getAuthors(
        @Query("_page") page: Int = DEFAULT_PAGE_NUMBER,
        @Query("_limit") limit: Int = DEFAULT_AUTHORS_PAGE_SIZE
    ): List<AuthorEntity>

    @GET("authors/{authorId}")
    suspend fun getAuthor(@Path("authorId") authorId: Int): AuthorEntity
}