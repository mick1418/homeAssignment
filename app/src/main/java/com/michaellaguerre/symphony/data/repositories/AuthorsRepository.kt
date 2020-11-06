package com.michaellaguerre.symphony.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.michaellaguerre.symphony.core.dependencies.Constants
import com.michaellaguerre.symphony.data.database.AppDatabase
import com.michaellaguerre.symphony.data.network.services.AuthorsService
import javax.inject.Inject

class AuthorsRepository
@Inject constructor(
    private val service: AuthorsService,
    private val database: AppDatabase
) {

    fun getAuthors(pageSize: Int = Constants.Api.DEFAULT_AUTHORS_PAGE_SIZE) = Pager(
        config = PagingConfig(pageSize, enablePlaceholders = true),
        remoteMediator = AuthorsRemoteMediator("authors", service, database)
    ) {
        database.authorDao().authorsPagingSource()
    }.liveData
}