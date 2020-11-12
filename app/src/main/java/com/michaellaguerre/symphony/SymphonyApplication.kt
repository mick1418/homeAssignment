package com.michaellaguerre.symphony

import android.app.Application
import com.michaellaguerre.symphony.core.di.ApplicationComponent
import com.michaellaguerre.symphony.core.di.modules.ApplicationModule
import com.michaellaguerre.symphony.core.di.DaggerApplicationComponent
import com.michaellaguerre.symphony.core.di.modules.NetworkModule

class SymphonyApplication: Application(){

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .networkModule(NetworkModule())
            .build()
    }


    //**********************************************************************************************
    // LIFECYCLE
    //**********************************************************************************************

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
    }

    private fun injectMembers() = appComponent.inject(this)

}