package com.michaellaguerre.symphony.data.network.services

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
     * @return a Call containing the list of [com.michaellaguerre.symphony.data.network.entities.PostEntity]
     * from a given [com.michaellaguerre.symphony.data.network.entities.AuthorEntity]
     */
    fun getPostsFromAuthor(authorId: Int) = postsApi.getPostsFromAuthor(authorId)

    /**
     * Retrieve the detail of a given [com.michaellaguerre.symphony.data.network.entities.PostEntity].
     *
     * @param postId the post's ID
     *
     * @return a Call containing the given [com.michaellaguerre.symphony.data.network.entities.PostEntity]
     */
    fun getPost(postId: Int) = postsApi.getPost(postId)

}