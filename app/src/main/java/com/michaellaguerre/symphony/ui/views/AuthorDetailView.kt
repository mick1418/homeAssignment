package com.michaellaguerre.symphony.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.google.android.material.card.MaterialCardView
import com.michaellaguerre.symphony.databinding.AuthorDetailsViewBinding
import com.michaellaguerre.symphony.databinding.AuthorsViewBinding

class AuthorDetailView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    MaterialCardView(context, attrs, defStyleAttr) {

    var binding: AuthorDetailsViewBinding

    init {
        binding = AuthorDetailsViewBinding.inflate(LayoutInflater.from(context), this)
    }

}