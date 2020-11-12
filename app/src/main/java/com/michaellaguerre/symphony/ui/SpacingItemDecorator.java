package com.michaellaguerre.symphony.ui;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Generic item decoration used to add spacing between views of a RecyclerView.
 * It supports Linear vertical and horizontal, and Gridview.
 */
public class SpacingItemDecorator extends RecyclerView.ItemDecoration {

    public static final int HORIZONTAL_LINEAR = 0;
    public static final int VERTICAL_LINEAR = 1;
    public static final int GRIDVIEW = 2;

    private int mLayoutType;
    private int mFullSpace;
    private int mHalfSpace;

    private boolean mShowFirstSpace;
    private boolean mShowLastSpace;


    public SpacingItemDecorator(int space, int layoutType, boolean showFirstSpace, boolean showLastSpace) {
        mFullSpace = space;
        mHalfSpace = mFullSpace / 2;
        mLayoutType = layoutType;
        mShowFirstSpace = showFirstSpace;
        mShowLastSpace = showLastSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        int position = parent.getChildAdapterPosition(view);

        switch (mLayoutType) {
            case HORIZONTAL_LINEAR:
                // Add full spacing only for the first item to avoid double space between items
                if (position == 0) {
                    outRect.left = mShowFirstSpace ? mFullSpace : 0;
                    outRect.right = mHalfSpace;
                }

                // Add full spacing only for the last item to avoid double space between items
                else if (position == (state.getItemCount() - 1)) {
                    outRect.left = mHalfSpace;
                    outRect.right = mShowLastSpace ? mFullSpace : 0;
                } else {
                    outRect.left = mHalfSpace;
                    outRect.right = mHalfSpace;
                }
                break;
            case VERTICAL_LINEAR:

                // Add full spacing only for the first item to avoid double space between items
                if (position == 0) {
                    outRect.top = mShowFirstSpace ? mFullSpace : 0;
                    outRect.bottom = mHalfSpace;
                }

                // Add full spacing only for the last item to avoid double space between items
                else if (position == (state.getItemCount() - 1)) {
                    outRect.top = mHalfSpace;
                    outRect.bottom = mShowLastSpace ? mFullSpace : 0;
                } else {
                    outRect.top = mHalfSpace;
                    outRect.bottom = mHalfSpace;
                }
                break;

            case GRIDVIEW:

                // Left item
                GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) view.getLayoutParams();

                if (lp.getSpanIndex() == 0) {
                    outRect.right = mHalfSpace;
                    outRect.left = mHalfSpace;
                }

                // Right item
                else if (lp.getSpanIndex() == ((GridLayoutManager) parent.getLayoutManager()).getSpanCount() - 1) {
                    outRect.left = mHalfSpace;
                    outRect.right = mHalfSpace;
                }

                // Center item
                else {
                    outRect.left = mHalfSpace;
                    outRect.right = mHalfSpace;
                }

                outRect.bottom = mFullSpace;
                break;
            default:
                break;
        }
    }
}
