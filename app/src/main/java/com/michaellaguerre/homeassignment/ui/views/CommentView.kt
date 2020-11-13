package com.michaellaguerre.homeassignment.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.michaellaguerre.homeassignment.databinding.CommentViewBinding


/**
 * View used to represent a post comment.
 *
 * It contains:
 * - the avatar
 * - the comment body
 * - the username
 * - the email
 * - the date
 */
class CommentView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    LinearLayout(context, attrs, defStyleAttr) {

    var binding: CommentViewBinding

    init {
        orientation = VERTICAL
        binding = CommentViewBinding.inflate(LayoutInflater.from(context), this)
        background = null
    }

}