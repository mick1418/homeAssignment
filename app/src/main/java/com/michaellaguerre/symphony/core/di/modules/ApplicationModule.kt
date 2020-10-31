package com.michaellaguerre.symphony.core.di.modules

import android.content.Context
import com.michaellaguerre.symphony.SymphonyApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: SymphonyApplication) {

    /**
     * Provides a singleton instance for the SymphonyApplication
     */
    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application

}