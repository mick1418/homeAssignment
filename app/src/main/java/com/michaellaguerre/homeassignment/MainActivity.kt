package com.michaellaguerre.homeassignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.michaellaguerre.homeassignment.core.di.ApplicationComponent

/**
 * Main [Activity] of the application.
 *
 * It contains the NavHostFragment that will handle all the navigation.
 */
class MainActivity : AppCompatActivity() {

    private val appComponent: ApplicationComponent by lazy {
        (application as MyApplication).appComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        // Inject dependencies from Dagger
        appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}