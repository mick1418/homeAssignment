package com.michaellaguerre.symphony.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.michaellaguerre.symphony.core.extensions.loadFromUrl
import com.michaellaguerre.symphony.domain.entities.Author
import com.michaellaguerre.symphony.domain.entities.Post
import com.michaellaguerre.symphony.ui.views.AuthorView
import com.michaellaguerre.symphony.ui.views.PostView
import javax.inject.Inject
import kotlin.properties.Delegates

class PostsAdapter
@Inject constructor() : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    /**
     * Helper delegate for call notifyDataSetChanged() each time the content of the field is updated
     */
    internal var collection: List<Post> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }


    //**********************************************************************************************
    // LIFECYCLE
    //**********************************************************************************************

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(PostView(parent.context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(collection[position])
    }

    override fun getItemCount() = collection.size


    //**********************************************************************************************
    // VIEW HOLDERS
    //**********************************************************************************************

    class ViewHolder(itemView: PostView) : RecyclerView.ViewHolder(itemView) {

        fun bind(post: Post) {
            itemView as PostView
            itemView.binding.postTitleTextView.text = post.title
            itemView.binding.postCoverImageView.loadFromUrl(post.imageUrl)
            itemView.binding.postDateTextView.text = post.date
            itemView.setOnClickListener {
                //TODO
            }
        }
    }
}
