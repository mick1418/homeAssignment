package com.michaellaguerre.symphony.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.michaellaguerre.symphony.R
import com.michaellaguerre.symphony.core.platform.BaseFragment
import com.michaellaguerre.symphony.databinding.AuthorsFragmentBinding
import com.michaellaguerre.symphony.domain.entities.Author
import com.michaellaguerre.symphony.ui.SpacingItemDecorator
import com.michaellaguerre.symphony.ui.adapters.AuthorsPagingAdapter
import com.michaellaguerre.symphony.ui.adapters.LoadingStateAdapter
import com.michaellaguerre.symphony.ui.viewmodels.AuthorsViewModel
import javax.inject.Inject

/**
 * Fragment representing the list of [Author].
 */
class AuthorsFragment : BaseFragment() {

    @Inject
    lateinit var authorsAdapter: AuthorsPagingAdapter

    private lateinit var viewModel: AuthorsViewModel

    // View binding
    private var _binding: AuthorsFragmentBinding? = null
    private val binding get() = _binding!!


    //**********************************************************************************************
    // LIFECYCLE
    //**********************************************************************************************

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        viewModel = ViewModelProvider(this).get(AuthorsViewModel::class.java)

        // Injecting the viewModel (do not know if it should be there...)
        appComponent.inject(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AuthorsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configureRecyclerView()
        configurePullToRefresh()

        retrieveAuthors()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


    //**********************************************************************************************
    // CONFIGURATION
    //**********************************************************************************************


    private fun configureRecyclerView() {

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = authorsAdapter.withLoadStateFooter(LoadingStateAdapter(authorsAdapter))

            authorsAdapter.clickListener = { author ->

                findNavController().navigate(
                    AuthorsFragmentDirections.actionAuthorsFragmentToAuthorDetailsFragment(
                        author = author!!
                    )
                )
            }

            val recyclerViewPadding = resources.getDimensionPixelSize(R.dimen.authors_fragment_list_spacing)
            val spacingDecorator = SpacingItemDecorator(
                recyclerViewPadding,
                SpacingItemDecorator.VERTICAL_LINEAR,
                false,
                false
            )
            addItemDecoration(spacingDecorator)
        }
    }

    private fun configurePullToRefresh() {

        authorsAdapter.addLoadStateListener { loadStates ->
            binding.swipeToRefresh.isRefreshing = loadStates.refresh is LoadState.Loading
        }

        binding.swipeToRefresh.setOnRefreshListener { retrieveAuthors() }
    }

    private fun retrieveAuthors() {

        // Start loading authors list
        viewModel.loadAuthors()

        // Observe the authors list
        viewModel.authors.observe(viewLifecycleOwner, { pagingData ->
            authorsAdapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
        })
    }
}