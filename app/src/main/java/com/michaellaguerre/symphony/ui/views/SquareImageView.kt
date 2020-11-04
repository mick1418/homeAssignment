package com.michaellaguerre.symphony.ui.views

import android.content.Context
import android.util.AttributeSet

/**
 * An ImageView that should remain square at all time.
 */
class SquareImageView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    androidx.appcompat.widget.AppCompatImageView(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }

}