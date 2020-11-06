package com.michaellaguerre.symphony.data.network.services

import com.michaellaguerre.symphony.core.dependencies.Constants
import com.michaellaguerre.symphony.data.network.api.CommentsApi
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommentsService
@Inject constructor(retrofit: Retrofit) {

    private val commentsApi by lazy { retrofit.create(CommentsApi::class.java) }

    /**
     * Retrieve the list of [com.michaellaguerre.symphony.data.network.entities.CommentEntity] from a
     * given [com.michaellaguerre.symphony.data.network.entities.PostEntity].
     *
     * The list is ordered chronologically descendant.
     *
     * @param postId the comment's ID
     *
     * @return a List of [com.michaellaguerre.symphony.data.network.entities.CommentEntity]
     * from a given [com.michaellaguerre.symphony.data.network.entities.PostEntity]
     */
    suspend fun getCommentsFromPost(
        postId: Int,
        pageNumber: Int = Constants.Api.DEFAULT_PAGE_NUMBER,
        limit: Int = Constants.Api.DEFAULT_POSTS_PAGE_SIZE
    ) = commentsApi.getCommentsFromPost(postId, pageNumber, limit)
}