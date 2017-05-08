package com.congnt.kotlinmvp.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class TouchHelperCallback extends ItemTouchHelper.Callback {
    private final BaseSimpleRecyclerAdapter mAdapter;
    private boolean canDrag = true;
    private boolean canSwipe = true;

    public TouchHelperCallback(BaseSimpleRecyclerAdapter mAdapter, boolean canSwipe, boolean canDrag) {
        this.mAdapter = mAdapter;
        this.canSwipe = canSwipe;
        this.canDrag = canDrag;
    }

    public TouchHelperCallback(BaseSimpleRecyclerAdapter adapter) {
        mAdapter = adapter;
    }


    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        if (canDrag && canSwipe) {
            return makeMovementFlags(dragFlags, swipeFlags);
        } else if (canDrag) {
            return makeFlag(ItemTouchHelper.ACTION_STATE_DRAG, dragFlags);
        } else if (canSwipe) {
            return makeFlag(ItemTouchHelper.ACTION_STATE_SWIPE, swipeFlags);
        }
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {
        mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }

}