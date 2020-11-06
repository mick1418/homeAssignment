package com.michaellaguerre.symphony.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.michaellaguerre.symphony.R
import com.michaellaguerre.symphony.core.extensions.gone
import com.michaellaguerre.symphony.core.extensions.loadFromUrl
import com.michaellaguerre.symphony.core.extensions.visible
import com.michaellaguerre.symphony.core.platform.BaseFragment
import com.michaellaguerre.symphony.core.utils.Failure
import com.michaellaguerre.symphony.data.Resource
import com.michaellaguerre.symphony.data.Status
import com.michaellaguerre.symphony.data.entities.PostEntity
import com.michaellaguerre.symphony.databinding.AuthorDetailsFragmentBinding
import com.michaellaguerre.symphony.domain.entities.Author
import com.michaellaguerre.symphony.domain.entities.Post
import com.michaellaguerre.symphony.ui.SpacingItemDecorator
import com.michaellaguerre.symphony.ui.adapters.LoadingStateAdapter
import com.michaellaguerre.symphony.ui.adapters.PostsPagingAdapter
import com.michaellaguerre.symphony.ui.viewmodels.AuthorDetailsViewModel
import javax.inject.Inject

class AuthorDetailsFragment : BaseFragment() {

    @Inject
    lateinit var postsAdapter: PostsPagingAdapter

    private lateinit var viewModel: AuthorDetailsViewModel

    // View binding
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

        // Injecting the viewModel (do not know if it should be there...)
        appComponent.inject(viewModel)

        configureRecyclerView()

        // Start loading authors list
        viewModel.loadPostsForAuthor(viewModel.author.value?.id!!)

        // Observe the posts list
        viewModel.posts.observe(viewLifecycleOwner, { pagingData ->
            postsAdapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
        })


        displayAuthor(viewModel.author.value!!)
    }


    //**********************************************************************************************
    // CONFIGURATION
    //**********************************************************************************************


    private fun configureRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = postsAdapter.withLoadStateFooter(LoadingStateAdapter(postsAdapter))

            postsAdapter.clickListener = { post ->
                findNavController().navigate(
                    AuthorDetailsFragmentDirections.actionAuthorDetailsFragmentToPostDetailsFragment(
                        post!!
                    )
                )
            }

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
    }


    //**********************************************************************************************
    // ACTIONS
    //**********************************************************************************************


    private fun displayAuthor(author: Author) {
        binding.authorDetailsView.binding.authorAvatarImageView.loadFromUrl(author.avatarUrl)
        binding.authorDetailsView.binding.authorNameTextView.text = author.name
        binding.authorDetailsView.binding.authorEmailTextView.text = author.email
        binding.authorDetailsView.binding.authorNickNameTextView.text = author.userName
    }

}