package com.michaellaguerre.homeassignment.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

/**
 * Item decoration used to add spacing and separator view between views of a RecyclerView.
 * It supports Linear vertical and horizontal.
 *
 * @param context        the context
 * @param drawableResID  the separator resource ID
 * @param spacing        the spacing between each item (in pixels)
 * @param showFirstSpace true if we want to add a space before the first item, false otherwise
 * @param showLastSpace  true if we want to add a space after the last item, false otherwise
 */
class DividerItemDecorator(
    context: Context,
    drawableResID: Int,
    spacing: Int,
    showFirstSpace: Boolean,
    showLastSpace: Boolean
) : ItemDecoration() {

    private var mDivider: Drawable? = null
    private var mShowFirstSpace = showFirstSpace
    private var mShowLastSpace = showLastSpace
    private var mFullSpace = spacing
    private var mHalfSpace = 0
    var mOrientation = -1

    init {
        mDivider = ContextCompat.getDrawable(context, drawableResID)
        mHalfSpace = mFullSpace / 2
    }


    /**
     * Retrieve any offsets for the given item. Each field of `outRect` specifies
     * the number of pixels that the item view should be inset by, similar to padding or margin.
     * The default implementation sets the bounds of outRect to 0 and returns.
     *
     * If this ItemDecoration does not affect the positioning of item views, it should set
     * all four fields of `outRect` (left, top, right, bottom) to zero
     * before returning.
     *
     * If you need to access Adapter for additional data, you can call
     * [RecyclerView.getChildAdapterPosition] to get the adapter position of the
     * View.
     *
     * @param outRect Rect to receive the output.
     * @param view    The child view to decorate
     * @param parent  RecyclerView this ItemDecoration is decorating
     * @param state   The current state of RecyclerView.
     */
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        // Get item position
        val position = parent.getChildAdapterPosition(view)

        // We are configuring a header or an invalid view -> do nothing
        if (position <= RecyclerView.NO_POSITION) {
            return
        }
        if (mOrientation == -1) getOrientation(parent)

        // Vertical
        if (mOrientation == LinearLayoutManager.VERTICAL) {

            // Add full spacing only for the first item to avoid double space between items
            if (position == 0) {
                outRect.top = if (mShowFirstSpace) mFullSpace else 0
                outRect.bottom =
                    mHalfSpace + if (mDivider == null) 0 else mDivider!!.intrinsicHeight
            } else if (position == state.itemCount - 1) {
                outRect.top = mHalfSpace
                outRect.bottom = if (mShowLastSpace) mFullSpace else 0
            } else {
                outRect.top = mHalfSpace
                outRect.bottom =
                    mHalfSpace + if (mDivider == null) 0 else mDivider!!.intrinsicHeight
            }
        } else {
            // Add full spacing only for the first item to avoid double space between items
            if (position == 0) {
                outRect.left = if (mShowFirstSpace) mFullSpace else 0
                outRect.right = mHalfSpace + if (mDivider == null) 0 else mDivider!!.intrinsicWidth
            } else if (position == state.itemCount - 1) {
                outRect.left = mHalfSpace
                outRect.right = if (mShowLastSpace) mFullSpace else 0
            } else {
                outRect.left = mHalfSpace
                outRect.right = mHalfSpace + if (mDivider == null) 0 else mDivider!!.intrinsicWidth
            }
        }
    }

    /**
     * Draw any appropriate decorations into the Canvas supplied to the RecyclerView.
     * Any content drawn by this method will be drawn after the item views are drawn
     * and will thus appear over the views.
     *
     *
     * It is used to draw the divider between the RecyclerView items.
     *
     * @param c      Canvas to draw into
     * @param parent RecyclerView this ItemDecoration is drawing into
     * @param state  The current state of RecyclerView.
     */
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (mDivider == null) {
            super.onDrawOver(c, parent, state)
            return
        }

        // Initialization needed to avoid compiler warning
        var left = 0
        var right = 0
        var top = 0
        var bottom = 0
        val size: Int
        val orientation = if (mOrientation != -1) mOrientation else getOrientation(parent)
        val childCount = parent.childCount
        val padding = Rect()
        mDivider!!.getPadding(padding)
        size = if (orientation == LinearLayoutManager.VERTICAL) {
            mDivider!!.intrinsicHeight
        } else { //horizontal
            mDivider!!.intrinsicWidth
        }
        for (i in 0 until childCount - 1) {
            val child = parent.getChildAt(i)
            if (orientation == LinearLayoutManager.VERTICAL) {
                top = parent.layoutManager!!.getDecoratedBottom(child) - size
                bottom = top + size
                left = parent.layoutManager!!.getDecoratedLeft(child)
                right = parent.layoutManager!!.getDecoratedRight(child)
            } else { //horizontal
                left = parent.layoutManager!!.getDecoratedRight(child) - size
                right = left + size
                top = parent.layoutManager!!.getDecoratedTop(child)
                bottom = parent.layoutManager!!.getDecoratedBottom(child)
            }

            // Draw the divider, taking into account its included padding
            mDivider!!.setBounds(
                left + padding.left,
                top + padding.top,
                right - padding.right,
                bottom - padding.bottom
            )
            mDivider!!.draw(c)
        }
    }

    /**
     * Retrieve the current orientation of the RecyclerView
     *
     * @param parent the RecyclerView
     * @return the orientation
     */
    private fun getOrientation(parent: RecyclerView): Int {
        if (mOrientation == -1) {
            mOrientation = if (parent.layoutManager is LinearLayoutManager) {
                val layoutManager = parent.layoutManager as LinearLayoutManager?
                layoutManager!!.orientation
            } else {
                throw IllegalStateException("DividerItemDecoration can only be used with a LinearLayoutManager.")
            }
        }
        return mOrientation
    }
}