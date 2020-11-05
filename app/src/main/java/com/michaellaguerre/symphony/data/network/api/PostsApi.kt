package com.michaellaguerre.symphony.data.network.api

import com.michaellaguerre.symphony.core.dependencies.Constants.Api.DEFAULT_PAGE_NUMBER
import com.michaellaguerre.symphony.core.dependencies.Constants.Api.DEFAULT_PAGE_SIZE
import com.michaellaguerre.symphony.data.entities.PostEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostsApi {

    @GET("authors/{authorId}/posts")
    fun getPostsFromAuthor(
        @Path("authorId") authorId: Int,
        @Query("_page") page: Int = DEFAULT_PAGE_NUMBER,
        @Query("_limit") limit: Int = DEFAULT_PAGE_SIZE
    ): Call<List<PostEntity>>

    @GET("posts/{postId}")
    fun getPost(@Path("postId") postId: Int): Call<PostEntity>
}