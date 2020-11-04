package com.michaellaguerre.symphony.core.di

import com.michaellaguerre.symphony.MainActivity
import com.michaellaguerre.symphony.SymphonyApplication
import com.michaellaguerre.symphony.core.di.modules.ApplicationModule
import com.michaellaguerre.symphony.core.di.modules.NetworkModule
import com.michaellaguerre.symphony.ui.fragments.AuthorsFragment
import com.michaellaguerre.symphony.ui.viewmodels.AuthorsViewModel
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

    // ViewModels
    fun inject(authorsViewModel: AuthorsViewModel)

}