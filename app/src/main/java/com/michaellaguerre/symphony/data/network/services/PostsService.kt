package com.michaellaguerre.symphony.data.network.services

import com.michaellaguerre.symphony.core.dependencies.Constants
import com.michaellaguerre.symphony.data.network.api.PostsApi
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostsService
@Inject constructor(retrofit: Retrofit) {

    private val postsApi by lazy { retrofit.create(PostsApi::class.java) }

    /**
     * Retrieve the list of [com.michaellaguerre.symphony.data.network.entities.PostEntity] from a
     * given [com.michaellaguerre.symphony.data.network.entities.AuthorEntity].
     *
     * The list is ordered chronologically descendant.
     *
     * @param authorId the author's ID
     *
     * @return a List of [com.michaellaguerre.symphony.data.network.entities.PostEntity]
     * from a given [com.michaellaguerre.symphony.data.network.entities.AuthorEntity]
     */
    suspend fun getPostsFromAuthor(
        authorId: Int,
        pageNumber: Int = Constants.Api.DEFAULT_PAGE_NUMBER,
        limit: Int = Constants.Api.DEFAULT_POSTS_PAGE_SIZE
    ) = postsApi.getPostsFromAuthor(authorId, pageNumber, limit)

    /**
     * Retrieve the detail of a given [com.michaellaguerre.symphony.data.network.entities.PostEntity].
     *
     * @param postId the post's ID
     *
     * @return a [com.michaellaguerre.symphony.data.network.entities.PostEntity]
     */
    suspend fun getPost(postId: Int) = postsApi.getPost(postId)

}