package com.michaellaguerre.symphony

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.michaellaguerre.symphony.core.di.ApplicationComponent
import com.michaellaguerre.symphony.data.network.entities.AuthorEntity
import com.michaellaguerre.symphony.data.network.services.AuthorsService
import com.michaellaguerre.symphony.ui.main.MainFragment
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
                MainFragment.newInstance()
            ).commitNow()
        }


        val result = authorService.getAuthors()

        result.enqueue(object: Callback<List<AuthorEntity>>{
            override fun onResponse(
                call: Call<List<AuthorEntity>>,
                response: Response<List<AuthorEntity>>
            ) {
                Log.e("Test", "Number of authors: " + response.body()?.size)
            }

            override fun onFailure(call: Call<List<AuthorEntity>>, t: Throwable) {
                Log.e("Test", "ERROR")
            }

        })
    }
}