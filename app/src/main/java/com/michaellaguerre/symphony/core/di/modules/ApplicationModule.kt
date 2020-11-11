package com.michaellaguerre.symphony.core.di.modules

import android.content.Context
import com.michaellaguerre.symphony.SymphonyApplication
import com.michaellaguerre.symphony.data.database.AppDatabase
import com.michaellaguerre.symphony.data.network.api.AuthorsApi
import com.michaellaguerre.symphony.data.network.api.CommentsApi
import com.michaellaguerre.symphony.data.network.api.PostsApi
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
     * @param remoteDataSource the [AuthorsApi] from Retrofit
     * @param database the [AppDatabase] from Room
     * @return a singleton of [AuthorsRepository]
     */
    @Singleton
    @Provides
    fun provideAuthorsRepository(
        remoteDataSource: AuthorsApi,
        database: AppDatabase
    ): AuthorsRepository =
        AuthorsRepository(remoteDataSource, database)


    /**
     * Provides an singleton of the [PostsRepository]
     *
     * @param remoteDataSource the [PostsApi] from Retrofit
     * @param database the [AppDatabase] from Room
     * @return a singleton of [PostsRepository]
     */
    @Singleton
    @Provides
    fun providePostsRepository(
        remoteDataSource: PostsApi,
        database: AppDatabase
    ): PostsRepository =
        PostsRepository(remoteDataSource, database)


    /**
     * Provides an singleton of the [CommentsRepository]
     *
     * @param remoteDataSource the [CommentsApi] from Retrofit
     * @param database the [AppDatabase] from Room
     * @return a singleton of [CommentsRepository]
     */
    @Singleton
    @Provides
    fun provideCommentsRepository(
        remoteDataSource: CommentsApi,
        database: AppDatabase
    ): CommentsRepository =
        CommentsRepository(remoteDataSource, database)

}