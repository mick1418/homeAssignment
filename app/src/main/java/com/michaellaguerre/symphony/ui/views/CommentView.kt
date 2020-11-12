package com.michaellaguerre.symphony.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.michaellaguerre.symphony.databinding.CommentViewBinding

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