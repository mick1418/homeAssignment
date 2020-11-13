package com.michaellaguerre.homeassignment.data.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.LoadType.*
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.michaellaguerre.homeassignment.data.database.AppDatabase
import com.michaellaguerre.homeassignment.data.entities.PostEntity
import com.michaellaguerre.homeassignment.data.entities.RemoteKey
import com.michaellaguerre.homeassignment.data.network.api.PostsApi
import retrofit2.HttpException
import java.io.IOException

/**
 * A Paging RemoteMediator used to mediate between network and database calls when retrieving a list
 * of [PostEntity].
 *
 * It will first try to load the page from the network, then save it to the database.
 *
 * @param authorId the id of the [AuthorEntity] whose list of [PostEntity] we want to retrieve
 * @param service the [PostsApi] used to retrieve [PostEntity] from the network
 * @param database the [AppDatabase] used to retrieve [PostEntity] from the database
 */
@OptIn(ExperimentalPagingApi::class)
class PostsRemoteMediator(
    private val authorId: Int,
    private val service: PostsApi,
    private val database: AppDatabase
) : RemoteMediator<Int, PostEntity>() {

    var postsDao = database.postDao()
    var remoteKeysDao = database.remoteKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PostEntity>
    ): MediatorResult {

        val query = "author$authorId"

        try {
            // Try to retrieve the page number we need to download next
            val loadKey = when (loadType) {

                // When the data is refreshed, we start from the first page again
                REFRESH -> 1

                // We do not need to prepend, since REFRESH will always load the first page in
                // the list. Immediately return, reporting end of pagination.
                PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)

                // We need to load another page
                APPEND -> {

                    // First query the database to retrieve a "bookmark" to the next page
                    val remoteKey = database.withTransaction {
                        remoteKeysDao.remoteKeyByQuery(query)
                    }

                    // When remoteKey is null (in case of no network on first load for example), return
                    // the first page to display the error indicator
                    if (remoteKey == null) {
                        1
                    }
                    // Return the next page id
                    else {
                        remoteKey.nextKey
                    }
                }
            }

            val pageSize = when (loadType) {
                REFRESH -> state.config.initialLoadSize
                else -> state.config.pageSize
            }

            val items = service.getPostsFromAuthor(authorId, loadKey, pageSize)

            database.withTransaction {

                // In case of a refresh, we want to delete everything related to the this author
                if (loadType == REFRESH) {
                    postsDao.deleteAllPostsForAuthor(authorId)
                    remoteKeysDao.deleteByQuery(query)
                }

                // Insert a "bookmark"" to the next page
                remoteKeysDao.insertOrReplace(RemoteKey(query, loadKey + 1))

                // Insert the newly downloaded data
                postsDao.insertAll(items)
            }

            // We decided that we will define the end of the pagination once there is no more items
            // in the current page (not ideal as we are making one call to many).
            // We should instead try to use the last page header from the response
            return MediatorResult.Success(endOfPaginationReached = items.isEmpty())
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }
}