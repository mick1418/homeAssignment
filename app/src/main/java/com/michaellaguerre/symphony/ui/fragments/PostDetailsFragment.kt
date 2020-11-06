package com.michaellaguerre.symphony.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.michaellaguerre.symphony.core.platform.BaseFragment
import com.michaellaguerre.symphony.databinding.PostDetailsFragmentBinding
import com.michaellaguerre.symphony.ui.adapters.CommentsPagingAdapter
import com.michaellaguerre.symphony.ui.adapters.LoadingStateAdapter
import com.michaellaguerre.symphony.ui.viewmodels.PostDetailsViewModel
import javax.inject.Inject

class PostDetailsFragment : BaseFragment() {

    @Inject
    lateinit var commentsAdapter: CommentsPagingAdapter

    private lateinit var viewModel: PostDetailsViewModel

    // View binding
    private lateinit var binding: PostDetailsFragmentBinding

    private val args: PostDetailsFragmentArgs by navArgs()


    //**********************************************************************************************
    // LIFECYCLE
    //**********************************************************************************************

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        // View model
        val factory = PostDetailsViewModel.Factory(args.post)
        viewModel = ViewModelProvider(this, factory).get(PostDetailsViewModel::class.java)
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
        
        // Injecting the viewModel (do not know if it should be there...)
        appComponent.inject(viewModel)

        configureRecyclerView()

        // Start loading comments list
        viewModel.loadCommentsForPost(viewModel.post.value?.id!!)

        // Observe the comments list
        viewModel.comments.observe(viewLifecycleOwner, { pagingData ->
            commentsAdapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
        })

    }


    //**********************************************************************************************
    // CONFIGURATION
    //**********************************************************************************************


    private fun configureRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = commentsAdapter.withLoadStateFooter(LoadingStateAdapter(commentsAdapter))

//            val recyclerViewPadding =
//                resources.getDimensionPixelSize(R.dimen.authors_fragment_grid_spacing)
//            val spacingDecorator = SpacingItemDecorator(
//                recyclerViewPadding,
//                SpacingItemDecorator.GRIDVIEW,
//                false,
//                false
//            )
//            addItemDecoration(spacingDecorator)
        }
    }
}