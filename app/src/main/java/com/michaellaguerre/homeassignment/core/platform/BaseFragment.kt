package com.michaellaguerre.homeassignment.core.platform

import androidx.fragment.app.Fragment
import com.michaellaguerre.homeassignment.MyApplication
import com.michaellaguerre.homeassignment.core.di.ApplicationComponent

/**
 * Base Fragment class with helper methods.
 *
 * @see Fragment
 */
abstract class BaseFragment : Fragment() {

    // Dagger ApplicationComponent
    val appComponent: ApplicationComponent by lazy {
        (activity?.application as MyApplication).appComponent
    }
}
