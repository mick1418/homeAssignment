package com.michaellaguerre.homeassignment.ui.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.michaellaguerre.homeassignment.R
import com.michaellaguerre.homeassignment.core.extensions.loadFromUrl
import com.michaellaguerre.homeassignment.domain.entities.Author
import com.michaellaguerre.homeassignment.ui.views.AuthorDetailView
import javax.inject.Inject

/**
 * Paging Adapter used to display a paging list of [Author].
 */
class AuthorsPagingAdapter
@Inject constructor() : PagingDataAdapter<Author, AuthorsPagingAdapter.AuthorViewHolder>(AUTHOR_COMPARATOR) {

    internal var clickListener: (Author?) -> Unit = { _ -> }


    //**********************************************************************************************
    // LIFECYCLE
    //**********************************************************************************************

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AuthorViewHolder {
        return AuthorViewHolder(AuthorDetailView(parent.context))
    }

    override fun onBindViewHolder(holder: AuthorViewHolder, position: Int) {

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

    class AuthorViewHolder(itemView: AuthorDetailView) : RecyclerView.ViewHolder(itemView) {

        fun bind(author: Author?, clickListener: (Author?) -> Unit) {
            itemView as AuthorDetailView
            itemView.binding.authorAvatarImageView.loadFromUrl(author?.avatarUrl ?: "", R.drawable.ic_placeholder_author)
            itemView.binding.authorNameTextView.text = author?.name
            itemView.binding.authorEmailTextView.text = author?.email
            itemView.binding.authorNickNameTextView.text = author?.userName
            itemView.setOnClickListener { clickListener(author) }
        }
    }


    //**********************************************************************************************
    // COMPANION OBJECT
    //**********************************************************************************************

    companion object {

        val AUTHOR_COMPARATOR = object : DiffUtil.ItemCallback<Author>() {
            override fun areContentsTheSame(oldItem: Author, newItem: Author): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: Author, newItem: Author): Boolean =
                oldItem.id == newItem.id
        }
    }
}