package com.michaellaguerre.symphony.core.di.modules

import com.michaellaguerre.symphony.core.dependencies.Constants
import com.michaellaguerre.symphony.core.utils.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule() {

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
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
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
}