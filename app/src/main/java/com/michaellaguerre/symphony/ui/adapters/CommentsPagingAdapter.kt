package com.michaellaguerre.symphony.ui.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.michaellaguerre.symphony.core.extensions.loadFromUrl
import com.michaellaguerre.symphony.domain.entities.Comment
import com.michaellaguerre.symphony.ui.utils.DateUtils
import com.michaellaguerre.symphony.ui.views.CommentView
import javax.inject.Inject

class CommentsPagingAdapter
@Inject constructor() :
    PagingDataAdapter<Comment, CommentsPagingAdapter.CommentViewHolder>(COMMENT_COMPARATOR) {


    internal var clickListener: (Comment?) -> Unit = { _ -> }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommentViewHolder {
        return CommentViewHolder(CommentView(parent.context))
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val item = getItem(position)
        // Note that item may be null. ViewHolder must support binding a
        // null item as a placeholder.
        holder.bind(item, clickListener)
    }


    //**********************************************************************************************
    // VIEW HOLDERS
    //**********************************************************************************************

    class CommentViewHolder(itemView: CommentView) : RecyclerView.ViewHolder(itemView) {

        fun bind(comment: Comment?, clickListener: (Comment?) -> Unit) {
            itemView as CommentView
            itemView.binding.commentNameTextView.text = comment?.userName
            itemView.binding.commentEmailTextView.text = comment?.email
            itemView.binding.commentDateTextView.text = DateUtils.getFormattedDateFromString(comment?.date, DateUtils.API_FORMAT, DateUtils.UI_FORMAT)
            itemView.binding.commentBodyTextView.text = comment?.body
            itemView.binding.commentAvatarImageView.loadFromUrl(comment?.avatarUrl ?: "")
        }
    }


    companion object {

        val COMMENT_COMPARATOR = object : DiffUtil.ItemCallback<Comment>() {
            override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean =
                oldItem.id == newItem.id
        }
    }
}