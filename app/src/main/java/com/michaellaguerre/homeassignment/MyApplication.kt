package com.michaellaguerre.homeassignment

import android.app.Application
import com.michaellaguerre.homeassignment.core.di.ApplicationComponent
import com.michaellaguerre.homeassignment.core.di.modules.ApplicationModule
import com.michaellaguerre.homeassignment.core.di.DaggerApplicationComponent
import com.michaellaguerre.homeassignment.core.di.modules.NetworkModule

open class MyApplication: Application(){

    lateinit var appComponent: ApplicationComponent


    //**********************************************************************************************
    // LIFECYCLE
    //**********************************************************************************************

    override fun onCreate() {
        super.onCreate()
        appComponent = getComponent()

        this.injectMembers()
    }

    open fun getComponent(): ApplicationComponent {
        return DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .networkModule(NetworkModule())
            .build()
    }

    private fun injectMembers() = appComponent.inject(this)

}