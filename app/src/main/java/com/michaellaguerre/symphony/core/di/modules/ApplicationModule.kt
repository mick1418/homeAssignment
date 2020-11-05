package com.michaellaguerre.symphony.core.di.modules

import android.content.Context
import com.michaellaguerre.symphony.SymphonyApplication
import com.michaellaguerre.symphony.core.platform.NetworkAvailabilityChecker
import com.michaellaguerre.symphony.data.database.daos.AuthorDao
import com.michaellaguerre.symphony.data.database.daos.CommentDao
import com.michaellaguerre.symphony.data.database.daos.PostDao
import com.michaellaguerre.symphony.data.network.services.AuthorsService
import com.michaellaguerre.symphony.data.network.services.CommentsService
import com.michaellaguerre.symphony.data.network.services.PostsService
import com.michaellaguerre.symphony.data.repositories.AuthorsRepository
import com.michaellaguerre.symphony.data.repositories.CommentsRepository
import com.michaellaguerre.symphony.data.repositories.PostsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: SymphonyApplication) {

    /**
     * Provides a singleton instance for the SymphonyApplication
     */
    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application


    //**********************************************************************************************
    // REPOSITORIES
    //**********************************************************************************************

    @Singleton
    @Provides
    fun provideAuthorsRepository(
        remoteDataSource: AuthorsService,
        localDataSource: AuthorDao,
        networkAvailabilityChecker: NetworkAvailabilityChecker
    ): AuthorsRepository =
        AuthorsRepository(remoteDataSource, localDataSource, networkAvailabilityChecker)

    @Singleton
    @Provides
    fun providePostsRepository(
        remoteDataSource: PostsService,
        localDataSource: PostDao,
        networkAvailabilityChecker: NetworkAvailabilityChecker
    ): PostsRepository =
        PostsRepository(remoteDataSource, localDataSource, networkAvailabilityChecker)

    @Singleton
    @Provides
    fun provideCommentsRepository(
        remoteDataSource: CommentsService,
        localDataSource: CommentDao,
        networkAvailabilityChecker: NetworkAvailabilityChecker
    ): CommentsRepository =
        CommentsRepository(remoteDataSource, localDataSource, networkAvailabilityChecker)

}