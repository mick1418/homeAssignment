package com.michaellaguerre.symphony.core.di

import com.michaellaguerre.symphony.MainActivity
import com.michaellaguerre.symphony.SymphonyApplication
import com.michaellaguerre.symphony.core.di.modules.ApplicationModule
import com.michaellaguerre.symphony.core.di.modules.DatabaseModule
import com.michaellaguerre.symphony.core.di.modules.NetworkModule
import com.michaellaguerre.symphony.ui.fragments.AuthorDetailsFragment
import com.michaellaguerre.symphony.ui.fragments.AuthorsFragment
import com.michaellaguerre.symphony.ui.fragments.PostDetailsFragment
import com.michaellaguerre.symphony.ui.viewmodels.AuthorDetailsViewModel
import com.michaellaguerre.symphony.ui.viewmodels.AuthorsViewModel
import com.michaellaguerre.symphony.ui.viewmodels.PostDetailsViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, DatabaseModule::class])
interface ApplicationComponent {

    //**********************************************************************************************
    // APPLICATION
    //**********************************************************************************************

    fun inject(application: SymphonyApplication)


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