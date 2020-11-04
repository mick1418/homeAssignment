package com.michaellaguerre.symphony.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.google.android.material.card.MaterialCardView
import com.michaellaguerre.symphony.databinding.AuthorsViewBinding

class AuthorView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    MaterialCardView(context, attrs, defStyleAttr) {

    var binding: AuthorsViewBinding

    init {
        binding = AuthorsViewBinding.inflate(LayoutInflater.from(context), this)
        radius = 40f
    }

}