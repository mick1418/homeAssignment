package com.michaellaguerre.symphony.core.platform

import androidx.fragment.app.Fragment
import com.michaellaguerre.symphony.SymphonyApplication
import com.michaellaguerre.symphony.core.di.ApplicationComponent

/**
 * Base Fragment class with helper methods.
 *
 * @see Fragment
 */
abstract class BaseFragment : Fragment() {

    // Dagger ApplicationComponent
    val appComponent: ApplicationComponent by lazy {
        (activity?.application as SymphonyApplication).appComponent
    }
}
