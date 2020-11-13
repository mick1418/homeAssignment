package com.michaellaguerre.homeassignment

import android.app.Application
import com.michaellaguerre.homeassignment.core.di.ApplicationComponent
import com.michaellaguerre.homeassignment.core.di.modules.ApplicationModule
import com.michaellaguerre.homeassignment.core.di.DaggerApplicationComponent
import com.michaellaguerre.homeassignment.core.di.modules.NetworkModule

class MyApplication: Application(){

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