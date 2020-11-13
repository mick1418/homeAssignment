package com.michaellaguerre.homeassignment.core.di

import com.michaellaguerre.homeassignment.MainActivity
import com.michaellaguerre.homeassignment.MyApplication
import com.michaellaguerre.homeassignment.core.di.modules.ApplicationModule
import com.michaellaguerre.homeassignment.core.di.modules.DatabaseModule
import com.michaellaguerre.homeassignment.core.di.modules.NetworkModule
import com.michaellaguerre.homeassignment.ui.fragments.AuthorDetailsFragment
import com.michaellaguerre.homeassignment.ui.fragments.AuthorsFragment
import com.michaellaguerre.homeassignment.ui.fragments.PostDetailsFragment
import com.michaellaguerre.homeassignment.ui.viewmodels.AuthorDetailsViewModel
import com.michaellaguerre.homeassignment.ui.viewmodels.AuthorsViewModel
import com.michaellaguerre.homeassignment.ui.viewmodels.PostDetailsViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, DatabaseModule::class])
interface ApplicationComponent {

    //**********************************************************************************************
    // APPLICATION
    //**********************************************************************************************

    fun inject(application: MyApplication)


    //**********************************************************************************************
    // ACTIVITIES
    //**********************************************************************************************

    fun inject(mainActivity: MainActivity)


    //**********************************************************************************************
    // FRAGMENTS
    //**********************************************************************************************

    fun inject(authorsFragment: AuthorsFragment)
    fun inject(authorDetailsFragment: AuthorDetailsFragment)
    fun inject(postDetailsFragment: PostDetailsFragment)


    //**********************************************************************************************
    // VIEW MODELS
    //**********************************************************************************************

    fun inject(authorsViewModel: AuthorsViewModel)
    fun inject(authorDetailsViewModel: AuthorDetailsViewModel)
    fun inject(postDetailsViewModel: PostDetailsViewModel)

}