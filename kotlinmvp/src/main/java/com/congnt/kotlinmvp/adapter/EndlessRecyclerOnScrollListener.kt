package com.congnt.kotlinmvp.adapter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log

/**
 * Using this class for Load More
 */
abstract class EndlessRecyclerOnScrollListener(private val mLinearLayoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    private var previousTotal = 0 // The total number of items in the dataset after the last load
    var isLoading = false
        private set // True if we are still waiting for the last set of data to load.
    private val visibleThreshold = 5 // The minimum amount of items to have below your current scroll position before loading more.
    internal var firstVisibleItem: Int = 0
    internal var visibleItemCount: Int = 0
    internal var totalItemCount: Int = 0

    private var current_page = 1

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        visibleItemCount = recyclerView!!.childCount
        totalItemCount = mLinearLayoutManager.itemCount
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition()

        if (totalItemCount == visibleItemCount) {
            return
        }

        if (!isLoading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {
            current_page++
            onLoadMore(current_page)
            isLoading = true
        }
    }

    fun restate() {
        isLoading = false
        previousTotal = totalItemCount
    }

    abstract fun onLoadMore(current_page: Int)

    companion object {
        var TAG = EndlessRecyclerOnScrollListener::class.java.simpleName
    }
}