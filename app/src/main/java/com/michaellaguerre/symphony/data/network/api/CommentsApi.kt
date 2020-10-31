package com.michaellaguerre.symphony.data.network.api

import com.michaellaguerre.symphony.core.dependencies.Constants.Api.DEFAULT_PAGE_NUMBER
import com.michaellaguerre.symphony.core.dependencies.Constants.Api.DEFAULT_PAGE_SIZE
import com.michaellaguerre.symphony.data.network.entities.CommentEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CommentsApi {

    @GET("posts/{postId}/comments")
    fun getCommentsFromPost(
        @Path("postId") postId: Int,
        @Query("_page") page: Int = DEFAULT_PAGE_NUMBER,
        @Query("_limit") limit: Int = DEFAULT_PAGE_SIZE
    ): Call<List<CommentEntity>>
}