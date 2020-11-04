package com.michaellaguerre.symphony.core.extensions

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.michaellaguerre.symphony.R

/**
 * Helper function used to load an url into an [ImageView] via Glide, with a crossfade animation
 *
 * @param url the image url
 */
fun ImageView.loadFromUrl(url: String) =
    Glide.with(this.context.applicationContext)
        .load(url)
        .error(R.drawable.ic_placeholder_picture)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)


/**
 * Helper function used to set the visibility of a view to [View.VISIBLE]
 */
fun View.visible() {
    this.visibility = View.VISIBLE
}


/**
 * Helper function used to set the visibility of a view to [View.INVISIBLE]
 */
fun View.invisible() {
    this.visibility = View.INVISIBLE
}

/**
 * Helper function used to set the visibility of a view to [View.GONE]
 */
fun View.gone() {
    this.visibility = View.GONE
}
