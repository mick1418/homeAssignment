package com.michaellaguerre.symphony.core.di.modules

import android.content.Context
import com.michaellaguerre.symphony.SymphonyApplication
import com.michaellaguerre.symphony.data.network.NetworkAuthorsRepository
import com.michaellaguerre.symphony.data.network.NetworkCommentsRepository
import com.michaellaguerre.symphony.data.network.NetworkPostsRepository
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


    @Provides
    @Singleton
    fun provideAuthorsRepository(dataSource: NetworkAuthorsRepository): AuthorsRepository = dataSource

    @Provides
    @Singleton
    fun providePostsRepository(dataSource: NetworkPostsRepository): PostsRepository = dataSource

    @Provides
    @Singleton
    fun provideCommentsRepository(dataSource: NetworkCommentsRepository): CommentsRepository = dataSource

}