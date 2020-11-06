package com.michaellaguerre.symphony.ui.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.michaellaguerre.symphony.core.extensions.loadFromUrl
import com.michaellaguerre.symphony.domain.entities.Author
import com.michaellaguerre.symphony.ui.views.AuthorView
import javax.inject.Inject

class AuthorsPagingAdapter
@Inject constructor() :PagingDataAdapter<Author, AuthorsPagingAdapter.AuthorViewHolder>(AUTHOR_COMPARATOR) {


    internal var clickListener: (Author?) -> Unit = { _ -> }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AuthorViewHolder {
        return AuthorViewHolder(AuthorView(parent.context))
    }

    override fun onBindViewHolder(holder: AuthorViewHolder, position: Int) {
        val item = getItem(position)
        // Note that item may be null. ViewHolder must support binding a
        // null item as a placeholder.
        holder.bind(item, clickListener)
    }


    //**********************************************************************************************
    // VIEW HOLDERS
    //**********************************************************************************************

    class AuthorViewHolder(itemView: AuthorView) : RecyclerView.ViewHolder(itemView) {

        fun bind(author: Author?, clickListener: (Author?) -> Unit) {
            itemView as AuthorView
            itemView.binding.authorNameTextView.text = author?.name
            itemView.binding.authorAvatarImageView.loadFromUrl(author?.avatarUrl ?:"")
            itemView.setOnClickListener { clickListener(author) }
        }
    }


    companion object {

        val AUTHOR_COMPARATOR = object : DiffUtil.ItemCallback<Author>() {
            override fun areContentsTheSame(oldItem: Author, newItem: Author): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: Author, newItem: Author): Boolean =
                oldItem.id == newItem.id
        }
    }
}