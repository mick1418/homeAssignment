package com.michaellaguerre.symphony

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.michaellaguerre.symphony.core.di.ApplicationComponent
import com.michaellaguerre.symphony.data.network.entities.AuthorEntity
import com.michaellaguerre.symphony.data.network.services.AuthorsService
import com.michaellaguerre.symphony.domain.entities.Post
import com.michaellaguerre.symphony.ui.fragments.AuthorDetailsFragment
import com.michaellaguerre.symphony.ui.fragments.AuthorsFragment
import com.michaellaguerre.symphony.ui.fragments.PostDetailsFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private val appComponent: ApplicationComponent by lazy {
        (application as SymphonyApplication).appComponent
    }

    @Inject lateinit var authorService: AuthorsService

    override fun onCreate(savedInstanceState: Bundle?) {

        // Inject dependencies from Dagger
        appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.container,
                PostDetailsFragment.newInstance(Post(1, "", "", "", "", 1))
            ).commitNow()
        }
    }
}