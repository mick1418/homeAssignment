package com.michaellaguerre.homeassignment.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.michaellaguerre.homeassignment.databinding.AuthorDetailsViewBinding


/**
 * View used to represent the author's details.
 *
 * It contains:
 * - the avatar
 * - the name
 * - the username
 * - the email
 */
class AuthorDetailView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    LinearLayout(context, attrs, defStyleAttr) {

    var binding: AuthorDetailsViewBinding

    init {
        orientation = VERTICAL
        binding = AuthorDetailsViewBinding.inflate(LayoutInflater.from(context), this)
    }

}