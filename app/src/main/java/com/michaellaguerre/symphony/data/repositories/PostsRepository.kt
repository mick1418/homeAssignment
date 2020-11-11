package com.michaellaguerre.symphony.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.michaellaguerre.symphony.core.dependencies.Constants
import com.michaellaguerre.symphony.data.database.AppDatabase
import com.michaellaguerre.symphony.data.network.api.PostsApi
import javax.inject.Inject

class PostsRepository
@Inject constructor(
    private val service: PostsApi,
    private val database: AppDatabase
) {

    fun getPostsForAuthors(authorId: Int, pageSize: Int = Constants.Api.DEFAULT_POSTS_PAGE_SIZE) =
        Pager(
            config = PagingConfig(pageSize, enablePlaceholders = true),
            remoteMediator = PostsRemoteMediator(authorId, service, database)
        ) {
            database.postDao().postsByAuthorPagingSource(authorId)
        }.liveData
}