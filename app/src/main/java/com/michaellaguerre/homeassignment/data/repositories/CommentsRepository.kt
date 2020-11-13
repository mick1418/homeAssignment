package com.michaellaguerre.homeassignment.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.michaellaguerre.homeassignment.core.dependencies.Constants
import com.michaellaguerre.homeassignment.data.database.AppDatabase
import com.michaellaguerre.homeassignment.data.network.api.CommentsApi
import javax.inject.Inject

/**
 * A Repository used to retrieve a paginated list of [CommentEntity].
 *
 * It will first try to load the page from the network, then save it to the database.
 *
 * @param service the [CommentsApi] used to retrieve [CommentEntity] from the network
 * @param database the [AppDatabase] used to retrieve [CommentEntity] from the database
 */
class CommentsRepository
@Inject constructor(
    private val service: CommentsApi,
    private val database: AppDatabase
) {

    /**
     * Retrieves a paginated list of [CommentEntity] for a given [PostEntity]
     *
     * @param postId the post ID
     * @param pageSize the page size
     * @return a LiveData<PagingData<CommentEntity>>
     */
    fun getCommentsForPost(postId: Int, pageSize: Int = Constants.Api.DEFAULT_COMMENTS_PAGE_SIZE) =
        Pager(
            config = PagingConfig(pageSize),
            remoteMediator = CommentsRemoteMediator(postId, service, database)
        ) {
            database.commentDao().commentsByPostPagingSource(postId)
        }.liveData
}