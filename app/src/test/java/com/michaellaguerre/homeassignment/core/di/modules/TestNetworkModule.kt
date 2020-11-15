package com.michaellaguerre.homeassignment.core.di.modules

import com.michaellaguerre.homeassignment.core.dependencies.Constants
import com.michaellaguerre.homeassignment.data.network.api.AuthorsApi
import com.michaellaguerre.homeassignment.data.network.api.CommentsApi
import com.michaellaguerre.homeassignment.data.network.api.PostsApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class TestNetworkModule: NetworkModule() {

    /**
     * Provides a singleton instance of [AuthorsApi].
     *
     * @param retrofit the [Retrofit] instance
     * @return an instance of [AuthorsApi]
     */
    @Provides
    @Singleton
    override fun provideAuthorsApi(retrofit: Retrofit): AuthorsApi {
        return retrofit.create(AuthorsApi::class.java)
    }

    /**
     * Provides a singleton instance of [PostsApi].
     *
     * @param retrofit the [Retrofit] instance
     * @return an instance of [AuthorsApi]
     */
    @Provides
    @Singleton
    override fun providePostsApi(retrofit: Retrofit): PostsApi {
        return retrofit.create(PostsApi::class.java)
    }

    /**
     * Provides a singleton instance of [CommentsApi].
     *
     * @param retrofit the [Retrofit] instance
     * @return an instance of [CommentsApi]
     */
    @Provides
    @Singleton
    override fun provideCommentsApi(retrofit: Retrofit): CommentsApi {
        return retrofit.create(CommentsApi::class.java)
    }
}