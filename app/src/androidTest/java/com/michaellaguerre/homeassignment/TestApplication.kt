package com.michaellaguerre.homeassignment

import com.michaellaguerre.homeassignment.core.di.ApplicationComponent
import com.michaellaguerre.homeassignment.core.di.DaggerApplicationComponent
import com.michaellaguerre.homeassignment.core.di.modules.TestApplicationModule
import com.michaellaguerre.homeassignment.core.di.modules.TestNetworkModule

class TestApplication : MyApplication() {

    override fun getComponent(): ApplicationComponent {
        return DaggerApplicationComponent
            .builder()
            .applicationModule(TestApplicationModule(this))
            .networkModule(TestNetworkModule())
            .build()
    }
}

