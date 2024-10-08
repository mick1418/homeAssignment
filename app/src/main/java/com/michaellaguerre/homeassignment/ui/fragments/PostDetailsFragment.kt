package com.michaellaguerre.homeassignment.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.michaellaguerre.homeassignment.R
import com.michaellaguerre.homeassignment.core.extensions.loadFromUrl
import com.michaellaguerre.homeassignment.core.platform.BaseFragment
import com.michaellaguerre.homeassignment.databinding.PostDetailsFragmentBinding
import com.michaellaguerre.homeassignment.domain.entities.Author
import com.michaellaguerre.homeassignment.domain.entities.Post
import com.michaellaguerre.homeassignment.ui.DividerItemDecorator
import com.michaellaguerre.homeassignment.ui.adapters.CommentsPagingAdapter
import com.michaellaguerre.homeassignment.ui.adapters.LoadingStateAdapter
import com.michaellaguerre.homeassignment.ui.utils.DateUtils
import com.michaellaguerre.homeassignment.ui.viewmodels.PostDetailsViewModel
import javax.inject.Inject

/**
 * Fragment representing the detail screen for a [Post].
 *
 * It contains 2 sections:
 * - the post's details
 * - the post's associated comments
 */
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
        val factory = PostDetailsViewModel.Factory(args.author, args.post)
        viewModel = ViewModelProvider(this, factory).get(PostDetailsViewModel::class.java)

        // Injecting the viewModel (do not know if it should be there...)
        appComponent.inject(viewModel)
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

        configureToolbar()
        configureRecyclerView()

        configurePost(viewModel.post.value!!)
        configureAuthor(viewModel.author.value!!)

        retrieveComments()
    }


    //**********************************************************************************************
    // CONFIGURATION
    //**********************************************************************************************

    private fun configureToolbar() {
        NavigationUI.setupWithNavController(binding.toolbar, findNavController())
    }


    private fun configureRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = commentsAdapter.withLoadStateFooter(LoadingStateAdapter(commentsAdapter))

            val spacingDecorator = DividerItemDecorator(
                requireContext(),
                R.drawable.comment_divider,
                0,
                showFirstSpace = false,
                showLastSpace = false
            )
            addItemDecoration(spacingDecorator)
        }
    }

    private fun configurePost(post: Post) {

        binding.apply {
            bannerImageView.loadFromUrl(post.imageUrl, R.drawable.ic_placeholder_post)
            toolbar.title = post.title
            postBody.text = post.body
            date.text = DateUtils.getFormattedDateFromString(
                post.date,
                DateUtils.API_FORMAT,
                DateUtils.UI_FORMAT
            )
            collapsingToolbarLayout.setCollapsedTitleTextColor(resources.getColor(R.color.post_view_name))
            collapsingToolbarLayout.setExpandedTitleColor(resources.getColor(R.color.post_view_name))
        }
    }

    private fun configureAuthor(author: Author) {

        binding.apply {
            authorAvatar.loadFromUrl(author.avatarUrl ?: "", R.drawable.ic_placeholder_author)
            authorName.text = author.name
        }
    }

    private fun retrieveComments() {

        // Start loading comments list
        viewModel.loadCommentsForPost(viewModel.post.value?.id!!)

        // Observe the comments list
        viewModel.comments.observe(viewLifecycleOwner, { pagingData ->
            commentsAdapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
        })
    }
}