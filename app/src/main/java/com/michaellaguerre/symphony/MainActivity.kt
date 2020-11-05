package com.michaellaguerre.symphony

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.michaellaguerre.symphony.core.di.ApplicationComponent
import com.michaellaguerre.symphony.data.network.services.AuthorsService
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private val appComponent: ApplicationComponent by lazy {
        (application as SymphonyApplication).appComponent
    }

    @Inject
    lateinit var authorService: AuthorsService

    override fun onCreate(savedInstanceState: Bundle?) {

        // Inject dependencies from Dagger
        appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}