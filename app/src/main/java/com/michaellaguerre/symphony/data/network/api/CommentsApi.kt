package com.michaellaguerre.symphony.data.network.api

import com.michaellaguerre.symphony.core.dependencies.Constants.Api.DEFAULT_COMMENTS_PAGE_SIZE
import com.michaellaguerre.symphony.core.dependencies.Constants.Api.DEFAULT_PAGE_NUMBER
import com.michaellaguerre.symphony.data.entities.CommentEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit service interface containing comment-related network calls
 */
interface CommentsApi {

    @GET("posts/{postId}/comments?_sort=date&_order=asc")
    suspend fun getCommentsFromPost(
        @Path("postId") postId: Int,
        @Query("_page") page: Int = DEFAULT_PAGE_NUMBER,
        @Query("_limit") limit: Int = DEFAULT_COMMENTS_PAGE_SIZE
    ): List<CommentEntity>
}