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
import com.michaellaguerre.symphony.databinding.PostDetailsFragmentBinding
import com.michaellaguerre.symphony.domain.entities.Author
import com.michaellaguerre.symphony.domain.entities.Comment
import com.michaellaguerre.symphony.domain.entities.Post
import com.michaellaguerre.symphony.ui.SpacingItemDecorator
import com.michaellaguerre.symphony.ui.adapters.CommentsAdapter
import com.michaellaguerre.symphony.ui.viewmodels.AuthorDetailsViewModel
import com.michaellaguerre.symphony.ui.viewmodels.PostDetailsViewModel
import javax.inject.Inject

class PostDetailsFragment : BaseFragment() {

    @Inject
    lateinit var commentsAdapter: CommentsAdapter

    private lateinit var viewModel: PostDetailsViewModel

    // View binding
    private lateinit var binding: PostDetailsFragmentBinding


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
        binding = PostDetailsFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PostDetailsViewModel::class.java)

        // Injecting the viewModel (do not know if it should be there...)
        appComponent.inject(viewModel)

        // Configure observers
        viewModel.comments.observe(this, ::handleSuccessComments)
        viewModel.failure.observe(this, ::handleFailure)

        viewModel.post.observe(this, ::handleSuccessPost)


        configureRecyclerView()

        // Start loading comments list
        viewModel.loadCommentsForPost(1)
        viewModel.loadPost(1)
    }


    //**********************************************************************************************
    // CONFIGURATION
    //**********************************************************************************************


    private fun configureRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 1)
            adapter = commentsAdapter

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


    private fun handleSuccessComments(comments: List<Comment>?) {

        commentsAdapter.collection = comments.orEmpty()

        binding.recyclerView.visible()
        binding.noData.gone()

        Log.e("RESULT", "Comments: " + comments?.size)

    }

    private fun handleSuccessPost(post: Post) {

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
        fun newInstance() = PostDetailsFragment()
    }


}