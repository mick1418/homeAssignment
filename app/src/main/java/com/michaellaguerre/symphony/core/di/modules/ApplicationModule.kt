package com.michaellaguerre.symphony.core.di.modules

import android.content.Context
import com.michaellaguerre.symphony.SymphonyApplication
import com.michaellaguerre.symphony.data.database.AppDatabase
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

    /**
     * Provides an singleton of the [AuthorsRepository]
     *
     * @param remoteDataSource the [AuthorsService] from Retrofit
     * @param database the [AppDatabase] from Room
     * @return a singleton of [AuthorsRepository]
     */
    @Singleton
    @Provides
    fun provideAuthorsRepository(
        remoteDataSource: AuthorsService,
        database: AppDatabase
    ): AuthorsRepository =
        AuthorsRepository(remoteDataSource, database)


    /**
     * Provides an singleton of the [PostsRepository]
     *
     * @param remoteDataSource the [PostsService] from Retrofit
     * @param database the [AppDatabase] from Room
     * @return a singleton of [PostsRepository]
     */
    @Singleton
    @Provides
    fun providePostsRepository(
        remoteDataSource: PostsService,
        database: AppDatabase
    ): PostsRepository =
        PostsRepository(remoteDataSource, database)


    /**
     * Provides an singleton of the [CommentsRepository]
     *
     * @param remoteDataSource the [CommentsService] from Retrofit
     * @param database the [AppDatabase] from Room
     * @return a singleton of [CommentsRepository]
     */
    @Singleton
    @Provides
    fun provideCommentsRepository(
        remoteDataSource: CommentsService,
        database: AppDatabase
    ): CommentsRepository =
        CommentsRepository(remoteDataSource, database)

}