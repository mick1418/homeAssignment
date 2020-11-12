package com.michaellaguerre.symphony.core.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

/**
 * Helper function used to load an url into an [ImageView] via Glide, with a crossfade animation
 *
 * @param url the image url
 */
fun ImageView.loadFromUrl(url: String, placeholder: Int) =
    Glide.with(this.context.applicationContext)
        .load(url)
        .error(placeholder)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
