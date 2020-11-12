package com.michaellaguerre.symphony.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.michaellaguerre.symphony.databinding.NoDataViewBinding

/**
 * View used to represent an empty state.
 *
 * It contains:
 * - a picto
 * - a message
 */
class EmptyStateView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    LinearLayout(context, attrs, defStyleAttr) {

    var binding: NoDataViewBinding

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER
        binding = NoDataViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

}