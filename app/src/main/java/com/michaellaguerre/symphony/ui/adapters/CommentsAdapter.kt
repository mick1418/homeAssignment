package com.michaellaguerre.symphony.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.michaellaguerre.symphony.core.extensions.loadFromUrl
import com.michaellaguerre.symphony.domain.entities.Author
import com.michaellaguerre.symphony.domain.entities.Comment
import com.michaellaguerre.symphony.ui.views.AuthorView
import com.michaellaguerre.symphony.ui.views.CommentView
import javax.inject.Inject
import kotlin.properties.Delegates

class CommentsAdapter
@Inject constructor() : RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

    /**
     * Helper delegate for call notifyDataSetChanged() each time the content of the field is updated
     */
    internal var collection: List<Comment> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }


    //**********************************************************************************************
    // LIFECYCLE
    //**********************************************************************************************

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CommentView(parent.context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(collection[position])
    }

    override fun getItemCount() = collection.size


    //**********************************************************************************************
    // VIEW HOLDERS
    //**********************************************************************************************

    class ViewHolder(itemView: CommentView) : RecyclerView.ViewHolder(itemView) {

        fun bind(comment: Comment) {
            itemView as CommentView
            itemView.binding.commentNameTextView.text = comment.userName
            itemView.binding.commentEmailTextView.text = comment.email
            itemView.binding.commentDateTextView.text = comment.date
            itemView.binding.commentBodyTextView.text = comment.body
            itemView.binding.commentAvatarImageView.loadFromUrl(comment.avatarUrl)
        }
    }
}
