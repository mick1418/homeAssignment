package com.michaellaguerre.symphony.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.michaellaguerre.symphony.core.extensions.loadFromUrl
import com.michaellaguerre.symphony.domain.entities.Author
import com.michaellaguerre.symphony.ui.views.AuthorView
import javax.inject.Inject
import kotlin.properties.Delegates

class AuthorsAdapter
@Inject constructor() : RecyclerView.Adapter<AuthorsAdapter.ViewHolder>() {

    /**
     * Helper delegate for call notifyDataSetChanged() each time the content of the field is updated
     */
    internal var collection: List<Author> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }


    //**********************************************************************************************
    // LIFECYCLE
    //**********************************************************************************************

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(AuthorView(parent.context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(collection[position])
    }

    override fun getItemCount() = collection.size


    //**********************************************************************************************
    // VIEW HOLDERS
    //**********************************************************************************************

    class ViewHolder(itemView: AuthorView) : RecyclerView.ViewHolder(itemView) {

        fun bind(author: Author) {
            itemView as AuthorView
            itemView.binding.authorNameTextView.text = author.name
            itemView.binding.authorAvatarImageView.loadFromUrl(author.avatarUrl)
            itemView.setOnClickListener {
                //TODO
            }
        }
    }
}
