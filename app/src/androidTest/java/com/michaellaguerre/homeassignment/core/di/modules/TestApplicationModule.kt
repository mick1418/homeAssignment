package com.michaellaguerre.homeassignment.core.di.modules

import android.app.Application
import android.content.Context
import com.michaellaguerre.homeassignment.MyApplication
import com.michaellaguerre.homeassignment.data.database.AppDatabase
import com.michaellaguerre.homeassignment.data.network.api.AuthorsApi
import com.michaellaguerre.homeassignment.data.network.api.CommentsApi
import com.michaellaguerre.homeassignment.data.network.api.PostsApi
import com.michaellaguerre.homeassignment.data.repositories.AuthorsRepository
import com.michaellaguerre.homeassignment.data.repositories.CommentsRepository
import com.michaellaguerre.homeassignment.data.repositories.PostsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TestApplicationModule(private val application: Application): ApplicationModule(application) {



}