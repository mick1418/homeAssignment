package com.michaellaguerre.symphony.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.google.android.material.card.MaterialCardView
import com.michaellaguerre.symphony.databinding.AuthorsViewBinding
import com.michaellaguerre.symphony.databinding.PostViewBinding

class PostView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    MaterialCardView(context, attrs, defStyleAttr) {

    var binding: PostViewBinding

    init {
        binding = PostViewBinding.inflate(LayoutInflater.from(context), this)
        radius = 40f
    }

}