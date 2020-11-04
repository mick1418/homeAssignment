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
import com.michaellaguerre.symphony.core.extensions.loadFromUrl
import com.michaellaguerre.symphony.core.extensions.visible
import com.michaellaguerre.symphony.core.platform.BaseFragment
import com.michaellaguerre.symphony.core.utils.Failure
import com.michaellaguerre.symphony.databinding.AuthorDetailsFragmentBinding
import com.michaellaguerre.symphony.domain.entities.Author
import com.michaellaguerre.symphony.domain.entities.Post
import com.michaellaguerre.symphony.ui.SpacingItemDecorator
import com.michaellaguerre.symphony.ui.adapters.PostsAdapter
import com.michaellaguerre.symphony.ui.viewmodels.AuthorDetailsViewModel
import javax.inject.Inject

class AuthorDetailsFragment : BaseFragment() {

    @Inject
    lateinit var postsAdapter: PostsAdapter

    private lateinit var viewModel: AuthorDetailsViewModel

    // View binding
    private lateinit var binding: AuthorDetailsFragmentBinding


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
        binding = com.michaellaguerre.symphony.databinding.AuthorDetailsFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AuthorDetailsViewModel::class.java)

        // Injecting the viewModel (do not know if it should be there...)
        appComponent.inject(viewModel)

        // Configure observers
        viewModel.posts.observe(this, ::handleSuccessPosts)
        viewModel.failure.observe(this, ::handleFailure)

        viewModel.author.observe(this, ::handleSuccessAuthor)


        configureRecyclerView()

        // Start loading authors list
        viewModel.loadPostsForAuthor(1)
        viewModel.loadAuthor(1)
    }


    //**********************************************************************************************
    // CONFIGURATION
    //**********************************************************************************************


    private fun configureRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 1)
            adapter = postsAdapter

            val recyclerViewPadding =
                resources.getDimensionPixelSize(R.dimen.authors_fragment_grid_spacing)
            val spacingDecorator = SpacingItemDecorator(
                recyclerViewPadding,
                SpacingItemDecorator.GRIDVIEW,
                false,
                false
            )
            addItemDecoration(spacingDecorator)
        }
//        moviesAdapter.clickListener = { movie, navigationExtras ->
//            navigator.showMovieDetails(activity!!, movie, navigationExtras)
//        }
    }


    //**********************************************************************************************
    // ACTIONS
    //**********************************************************************************************


    private fun handleSuccessPosts(posts: List<Post>?) {

        postsAdapter.collection = posts.orEmpty()

        binding.recyclerView.visible()
        binding.noData.gone()

        Log.e("RESULT", "Authors: " + posts?.size)

    }

    private fun handleSuccessAuthor(author: Author) {

        binding.authorDetailsView.binding.authorAvatarImageView.loadFromUrl(author.avatarUrl)
        binding.authorDetailsView.binding.authorNameTextView.text = author.name
        binding.authorDetailsView.binding.authorEmailTextView.text = author.email
        binding.authorDetailsView.binding.authorNickNameTextView.text = author.userName


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
        fun newInstance() = AuthorDetailsFragment()
    }


}