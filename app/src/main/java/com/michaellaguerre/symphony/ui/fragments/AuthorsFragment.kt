package com.michaellaguerre.symphony.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.michaellaguerre.symphony.R
import com.michaellaguerre.symphony.core.extensions.gone
import com.michaellaguerre.symphony.core.extensions.visible
import com.michaellaguerre.symphony.core.platform.BaseFragment
import com.michaellaguerre.symphony.core.utils.Failure
import com.michaellaguerre.symphony.databinding.AuthorsFragmentBinding
import com.michaellaguerre.symphony.domain.entities.Author
import com.michaellaguerre.symphony.ui.SpacingItemDecorator
import com.michaellaguerre.symphony.ui.adapters.AuthorsAdapter
import com.michaellaguerre.symphony.ui.viewmodels.AuthorsViewModel
import javax.inject.Inject

class AuthorsFragment : BaseFragment() {

    @Inject
    lateinit var authorsAdapter: AuthorsAdapter

    private lateinit var viewModel: AuthorsViewModel

    // View binding
    private lateinit var binding: AuthorsFragmentBinding


    //**********************************************************************************************
    // LIFECYCLE
    //**********************************************************************************************

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AuthorsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AuthorsViewModel::class.java)

        // Injecting the viewModel (do not know if it should be there...)
        appComponent.inject(viewModel)

        // Configure observers
        viewModel.authors.observe(this, ::handleSuccess)
        viewModel.failure.observe(this, ::handleFailure)


        configureRecyclerView()

        // Start loading authors list
        viewModel.loadAuthors()
    }


    //**********************************************************************************************
    // CONFIGURATION
    //**********************************************************************************************


    private fun configureRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = authorsAdapter

            val recyclerViewPadding = resources.getDimensionPixelSize(R.dimen.authors_fragment_grid_spacing)
            val spacingDecorator =  SpacingItemDecorator(recyclerViewPadding, SpacingItemDecorator.GRIDVIEW, true, true)
            addItemDecoration(spacingDecorator)
        }
//        moviesAdapter.clickListener = { movie, navigationExtras ->
//            navigator.showMovieDetails(activity!!, movie, navigationExtras)
//        }
    }


    //**********************************************************************************************
    // ACTIONS
    //**********************************************************************************************

    private fun handleSuccess(authors: List<Author>?) {

        authorsAdapter.collection = authors.orEmpty()

        binding.recyclerView.visible()
        binding.noData.gone()

        Log.e("RESULT", "Authors: " + authors?.size)

    }

    private fun handleFailure(failure: Failure?) {
        Log.e("RESULT", "Error: " + failure?.toString())

        binding.recyclerView.gone()
        binding.noData.visible()
    }


    //**********************************************************************************************
    // COMPANION OBJECT
    //**********************************************************************************************

    companion object {
        fun newInstance() = AuthorsFragment()
    }


}