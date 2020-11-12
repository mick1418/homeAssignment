package com.michaellaguerre.symphony.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.michaellaguerre.symphony.core.dependencies.Constants
import com.michaellaguerre.symphony.data.database.AppDatabase
import com.michaellaguerre.symphony.data.entities.AuthorEntity
import com.michaellaguerre.symphony.data.network.api.AuthorsApi
import javax.inject.Inject


/**
 * A Repository used to retrieve a paginated list of [AuthorEntity].
 *
 * It will first try to load the page from the network, then save it to the database.
 *
 * @param service the [AuthorsApi] used to retrieve [AuthorEntity] from the network
 * @param database the [AppDatabase] used to retrieve [AuthorEntity] from the database
 */
class AuthorsRepository
@Inject constructor(
    private val service: AuthorsApi,
    private val database: AppDatabase
) {

    /**
     * Retrieves a paginated list of [AuthorEntity]
     *
     * @param pageSize the page size
     * @return a LiveData<PagingData<AuthorEntity>>
     */
    fun getAuthors(pageSize: Int = Constants.Api.DEFAULT_AUTHORS_PAGE_SIZE) = Pager(
        config = PagingConfig(pageSize, enablePlaceholders = true),
        remoteMediator = AuthorsRemoteMediator("authors", service, database)
    ) {
        database.authorDao().authorsPagingSource()
    }.liveData
}