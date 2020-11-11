package com.michaellaguerre.symphony.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.michaellaguerre.symphony.core.dependencies.Constants
import com.michaellaguerre.symphony.data.database.AppDatabase
import com.michaellaguerre.symphony.data.network.api.CommentsApi
import javax.inject.Inject

class CommentsRepository
@Inject constructor(
    private val service: CommentsApi,
    private val database: AppDatabase
) {

    fun getCommentsForPost(postId: Int, pageSize: Int = Constants.Api.DEFAULT_COMMENTS_PAGE_SIZE) =
        Pager(
            config = PagingConfig(pageSize),
            remoteMediator = CommentsRemoteMediator(postId, service, database)
        ) {
            database.commentDao().commentsByPostPagingSource(postId)
        }.liveData
}