package com.michaellaguerre.homeassignment.data.network.api

import com.michaellaguerre.homeassignment.core.dependencies.Constants.Api.DEFAULT_PAGE_NUMBER
import com.michaellaguerre.homeassignment.core.dependencies.Constants.Api.DEFAULT_POSTS_PAGE_SIZE
import com.michaellaguerre.homeassignment.data.entities.PostEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit service interface containing post-related network calls
 */
interface PostsApi {

    @GET("authors/{authorId}/posts?_sort=date&_order=desc")
    suspend fun getPostsFromAuthor(
        @Path("authorId") authorId: Int,
        @Query("_page") page: Int = DEFAULT_PAGE_NUMBER,
        @Query("_limit") limit: Int = DEFAULT_POSTS_PAGE_SIZE
    ): List<PostEntity>

    @GET("posts/{postId}")
    suspend fun getPost(@Path("postId") postId: Int): PostEntity
}