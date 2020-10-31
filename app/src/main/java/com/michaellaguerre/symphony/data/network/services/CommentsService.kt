package com.michaellaguerre.symphony.data.network.services

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
     * @return a Call containing the list of [com.michaellaguerre.symphony.data.network.entities.CommentEntity]
     * from a given [com.michaellaguerre.symphony.data.network.entities.PostEntity]
     */
    fun getCommentsFromPost(postId: Int) = commentsApi.getCommentsFromPost(postId)
}