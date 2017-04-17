package com.congnt.kotlinmvp.adapter

import android.support.v7.widget.RecyclerView

/**
 * Using this class for handle on Load more listener
 */
abstract class OnVerticalScrollListener : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        if (!recyclerView!!.canScrollVertically(-1)) {
            onScrolledToTop()
        } else if (!recyclerView.canScrollVertically(1)) {
            onScrolledToBottom()
        } else if (dy < 0) {
            onScrolledUp()
        } else if (dy > 0) {
            onScrolledDown()
        }
    }

    abstract fun onScrolledUp()

    abstract fun onScrolledDown()

    abstract fun onScrolledToTop()

    abstract fun onScrolledToBottom()
}