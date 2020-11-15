package com.michaellaguerre.homeassignment.utilities

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.michaellaguerre.homeassignment.TestApplication

// A custom runner to set up the instrumented application class for tests.
class MainTestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, name: String?, context: Context?): Application {
        return super.newApplication(cl, TestApplication::class.java.name, context)
    }
}
