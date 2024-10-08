package com.michaellaguerre.homeassignment.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.michaellaguerre.homeassignment.R
import com.michaellaguerre.homeassignment.core.extensions.loadFromUrl
import com.michaellaguerre.homeassignment.core.platform.BaseFragment
import com.michaellaguerre.homeassignment.databinding.AuthorDetailsFragmentBinding
import com.michaellaguerre.homeassignment.domain.entities.Author
import com.michaellaguerre.homeassignment.ui.adapters.LoadingStateAdapter
import com.michaellaguerre.homeassignment.ui.adapters.PostsPagingAdapter
import com.michaellaguerre.homeassignment.ui.viewmodels.AuthorDetailsViewModel
import javax.inject.Inject

/**
 * Fragment representing the detail screen for an [Author].
 *
 * It contains 2 sections:
 * - the author's details
 * - the author's posts
 */
class AuthorDetailsFragment : BaseFragment() {

    @Inject
    lateinit var postsAdapter: PostsPagingAdapter

    private lateinit var viewModel: AuthorDetailsViewModel
    private lateinit var binding: AuthorDetailsFragmentBinding

    private val args: AuthorDetailsFragmentArgs by navArgs()


    //**********************************************************************************************
    // LIFECYCLE
    //**********************************************************************************************

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        // View model
        val factory = AuthorDetailsViewModel.Factory(args.author)
        viewModel = ViewModelProvider(this, factory).get(AuthorDetailsViewModel::class.java)

        // Injecting the viewModel (do not know if it should be there...)
        appComponent.inject(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AuthorDetailsFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configureToolbar()
        configureAuthorDetails(viewModel.author.value!!)
        configurePullToRefresh()
        configureEmptyView()
        configureRecyclerView()
        retrievePosts()
    }


    //**********************************************************************************************
    // CONFIGURATION
    //**********************************************************************************************

    private fun configureToolbar() {
        NavigationUI.setupWithNavController(binding.toolbar, findNavController())
        binding.toolbar.title = ""
    }

    @OptIn(ExperimentalPagingApi::class)
    private fun configureEmptyView() {
        binding.noData.binding.apply {
            noDataPicto.setImageResource(R.drawable.ic_empty_post)
            noDataMessage.text = getText(R.string.author_details_no_data)
        }

        postsAdapter.addDataRefreshListener { isEmpty ->
            // Display the empty state if needed
            binding.noData.isVisible = isEmpty
        }
    }

    private fun configureRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = postsAdapter.withLoadStateFooter(LoadingStateAdapter(postsAdapter))

            postsAdapter.clickListener = { post ->
                findNavController().navigate(
                    AuthorDetailsFragmentDirections.actionAuthorDetailsFragmentToPostDetailsFragment(
                        post = post!!,
                        author = viewModel.author.value!!
                    )
                )
            }
        }
    }

    private fun configurePullToRefresh() {

        postsAdapter.addLoadStateListener { loadStates ->
            binding.swipeToRefresh.isRefreshing = loadStates.refresh is LoadState.Loading
        }

        binding.swipeToRefresh.setOnRefreshListener { retrievePosts() }
    }

    private fun configureAuthorDetails(author: Author) {

        // Configure the author view
        binding.authorDetailsView.binding.apply {
            authorAvatarImageView.loadFromUrl(author.avatarUrl, R.drawable.ic_placeholder_author)
            authorNameTextView.text = author.name
            authorEmailTextView.text = author.email
            authorNickNameTextView.text = author.userName
        }

        // Configure the FAB
        binding.fab.setOnClickListener { viewModel.contactByMail(requireContext(), author.email) }
    }


    //**********************************************************************************************
    // ACTIONS
    //**********************************************************************************************

    private fun retrievePosts() {

        // Start loading authors list
        viewModel.loadPostsForAuthor(viewModel.author.value?.id!!)

        // Observe the posts list
        viewModel.posts.observe(viewLifecycleOwner, { pagingData ->
            postsAdapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
        })
    }


}