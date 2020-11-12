package com.michaellaguerre.symphony.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.michaellaguerre.symphony.databinding.PostViewBinding


/**
 * View used to represent a post.
 *
 * It contains:
 * - the post image
 * - the post body
 * - the post title
 * - the post date
 */
class PostView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    LinearLayout(context, attrs, defStyleAttr) {

    var binding: PostViewBinding

    init {
        orientation = VERTICAL
        binding = PostViewBinding.inflate(LayoutInflater.from(context), this)
    }

}