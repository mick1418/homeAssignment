package com.michaellaguerre.homeassignment.ui.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.michaellaguerre.homeassignment.R
import com.michaellaguerre.homeassignment.core.extensions.loadFromUrl
import com.michaellaguerre.homeassignment.domain.entities.Post
import com.michaellaguerre.homeassignment.ui.utils.DateUtils
import com.michaellaguerre.homeassignment.ui.views.PostView
import javax.inject.Inject

/**
 * Paging Adapter used to display a paging list of [Post].
 */
class PostsPagingAdapter
@Inject constructor() : PagingDataAdapter<Post, PostsPagingAdapter.PostViewHolder>(POST_COMPARATOR) {

    internal var clickListener: (Post?) -> Unit = { _ -> }


    //**********************************************************************************************
    // LIFECYCLE
    //**********************************************************************************************

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostViewHolder {
        return PostViewHolder(PostView(parent.context))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        val params = RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
        holder.itemView.layoutParams = params

        val item = getItem(position)
        holder.bind(item, clickListener)
    }


    //**********************************************************************************************
    // VIEW HOLDERS
    //**********************************************************************************************

    class PostViewHolder(itemView: PostView) : RecyclerView.ViewHolder(itemView) {

        fun bind(post: Post?, clickListener: (Post?) -> Unit) {
            itemView as PostView
            itemView.binding.postTitleTextView.text = post?.title
            itemView.binding.postBodyTextView.text = post?.body
            itemView.binding.postCoverImageView.loadFromUrl(post?.imageUrl ?: "", R.drawable.ic_placeholder_post)
            itemView.binding.postDateTextView.text = DateUtils.getFormattedDateFromString(
                post?.date,
                DateUtils.API_FORMAT,
                DateUtils.UI_FORMAT
            )
            itemView.setOnClickListener { clickListener(post) }
        }
    }


    //**********************************************************************************************
    // COMPANION OBJECT
    //**********************************************************************************************

    companion object {

        val POST_COMPARATOR = object : DiffUtil.ItemCallback<Post>() {
            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
                oldItem.id == newItem.id
        }
    }
}