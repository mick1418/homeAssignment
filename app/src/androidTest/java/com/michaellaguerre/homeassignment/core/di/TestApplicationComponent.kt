package com.michaellaguerre.homeassignment.core.di

import com.michaellaguerre.homeassignment.MainActivity
import com.michaellaguerre.homeassignment.MyApplication
import com.michaellaguerre.homeassignment.core.di.modules.*
import com.michaellaguerre.homeassignment.core.di.scopes.ApplicationScope
import com.michaellaguerre.homeassignment.ui.fragments.AuthorDetailsFragment
import com.michaellaguerre.homeassignment.ui.fragments.AuthorsFragment
import com.michaellaguerre.homeassignment.ui.fragments.PostDetailsFragment
import com.michaellaguerre.homeassignment.ui.viewmodels.AuthorDetailsViewModel
import com.michaellaguerre.homeassignment.ui.viewmodels.AuthorsViewModel
import com.michaellaguerre.homeassignment.ui.viewmodels.PostDetailsViewModel
import dagger.Component

@ApplicationScope
@Component(modules = [TestApplicationModule::class, TestNetworkModule::class, DatabaseModule::class])
interface TestApplicationComponent {

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