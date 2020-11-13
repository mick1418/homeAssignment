package com.michaellaguerre.homeassignment.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.michaellaguerre.homeassignment.R
import com.michaellaguerre.homeassignment.core.platform.BaseFragment
import com.michaellaguerre.homeassignment.databinding.AuthorsFragmentBinding
import com.michaellaguerre.homeassignment.domain.entities.Author
import com.michaellaguerre.homeassignment.ui.DividerItemDecorator
import com.michaellaguerre.homeassignment.ui.adapters.AuthorsPagingAdapter
import com.michaellaguerre.homeassignment.ui.adapters.LoadingStateAdapter
import com.michaellaguerre.homeassignment.ui.viewmodels.AuthorsViewModel
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

        configureEmptyView()
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

    @OptIn(ExperimentalPagingApi::class)
    private fun configureEmptyView() {
        binding.noData.binding.apply {
            noDataPicto.setImageResource(R.drawable.ic_empty_author)
            noDataMessage.text = getText(R.string.author_list_no_data)
        }

        authorsAdapter.addDataRefreshListener { isEmpty ->
            // Display the empty state if needed
            binding.noData.isVisible = isEmpty
        }
    }


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

            val dividerDecorator = DividerItemDecorator(
                requireContext(),
                R.drawable.author_divider,
                0,
                showFirstSpace = false,
                showLastSpace = false
            )
            addItemDecoration(dividerDecorator)
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