package com.michaellaguerre.symphony.core.dependencies

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory




object Providers {

    fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.RetrofitConfiguration.BASE_URL)
            .client(okHttpClient())
            .addConverterFactory(gsonConverter())
            .build()


    }

    fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    fun gsonConverter(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }
}