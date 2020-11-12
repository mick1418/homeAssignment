package com.michaellaguerre.symphony.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.michaellaguerre.symphony.core.dependencies.Constants
import com.michaellaguerre.symphony.data.database.AppDatabase
import com.michaellaguerre.symphony.data.network.api.PostsApi
import javax.inject.Inject

/**
 * A Repository used to retrieve a paginated list of [PostEntity].
 *
 * It will first try to load the page from the network, then save it to the database.
 *
 * @param service the [PostsApi] used to retrieve [PostEntity] from the network
 * @param database the [AppDatabase] used to retrieve [PostEntity] from the database
 */
class PostsRepository
@Inject constructor(
    private val service: PostsApi,
    private val database: AppDatabase
) {


    /**
     * Retrieves a paginated list of [PostEntity] for a given [AuthorEntity]
     *
     * @param authorId the author ID
     * @param pageSize the page size
     * @return a LiveData<PagingData<PostEntity>>
     */
    fun getPostsForAuthor(authorId: Int, pageSize: Int = Constants.Api.DEFAULT_POSTS_PAGE_SIZE) =
        Pager(
            config = PagingConfig(pageSize, enablePlaceholders = true),
            remoteMediator = PostsRemoteMediator(authorId, service, database)
        ) {
            database.postDao().postsByAuthorPagingSource(authorId)
        }.liveData
}