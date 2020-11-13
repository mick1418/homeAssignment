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
class NetworkModule {

    /**
     * Provides a singleton instance of Retrofit
     *
     * @param okHttpClient an instance of okHttpClient used to configure Retrofit
     * @return an instance of Retrofit
     */
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.RetrofitConfiguration.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Provides a singleton instance of OkHttpClient.
     * It will have timeout of 60 seconds.
     *
     * @return an instance of OkHttpClient
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
        }.build()
    }


    /**
     * Provides a singleton instance of [AuthorsApi].
     *
     * @param retrofit the [Retrofit] instance
     * @return an instance of [AuthorsApi]
     */
    @Provides
    @Singleton
    fun provideAuthorsApi(retrofit: Retrofit): AuthorsApi {
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
    fun providePostsApi(retrofit: Retrofit): PostsApi {
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
    fun provideCommentsApi(retrofit: Retrofit): CommentsApi {
        return retrofit.create(CommentsApi::class.java)
    }
}