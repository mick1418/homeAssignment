package com.michaellaguerre.symphony.core.di

import com.michaellaguerre.symphony.MainActivity
import com.michaellaguerre.symphony.SymphonyApplication
import com.michaellaguerre.symphony.core.di.modules.ApplicationModule
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
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {

    // Application
    fun inject(application: SymphonyApplication)

    // Activities
    fun inject(mainActivity: MainActivity)

    // Fragments
    fun inject(authorsFragment: AuthorsFragment)
    fun inject(authorDetailsFragment: AuthorDetailsFragment)
    fun inject(postDetailsFragment: PostDetailsFragment)

    // ViewModels
    fun inject(authorsViewModel: AuthorsViewModel)
    fun inject(authorDetailsViewModel: AuthorDetailsViewModel)
    fun inject(postDetailsViewModel: PostDetailsViewModel)

}