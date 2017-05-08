package com.congnt.kotlinmvp.adapter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.congnt.kotlinmvp.R
import java.util.*

/**
 * Created by congn_000 on 9/14/2016.
 */
abstract class BaseSimpleRecyclerAdapter<T>(context: Context, protected var mList: MutableList<T?>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val mLayoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    val LOADING = 69

    lateinit var onClickListener: BaseSimpleRecyclerAdapter.OnClickListener<T>

    protected val itemLayoutLoadingId = R.layout.layout_loading

    protected abstract val itemLayoutId: Int

    protected abstract fun getViewHolder(itemView: View): RecyclerView.ViewHolder

    protected abstract fun bindHolder(holder: RecyclerView.ViewHolder, position: Int)

    fun setOnScrollListener(rc: RecyclerView, body: (listener: EndlessRecyclerOnScrollListener)->Unit){
        rc.setOnScrollListener(object : EndlessRecyclerOnScrollListener(rc.layoutManager as LinearLayoutManager){
            override fun onLoadMore(current_page: Int) {
                showLoading()
                body(this)
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == LOADING) {
            val item = mLayoutInflater.inflate(itemLayoutLoadingId, parent, false)
            return LoadingViewHolder(item)
        } else {
            val itemView = mLayoutInflater.inflate(itemLayoutId, parent, false)
            return getViewHolder(itemView)
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LoadingViewHolder) {
            return
        }
        bindHolder(holder, position)
        holder.itemView.setOnClickListener {
            onClickListener!!.onClick(mList[position], position)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun getItemViewType(position: Int): Int {
        if (mList[position] == null) {
            return LOADING
        }
        return super.getItemViewType(position)
    }

    var cacheLoadingPos = -1
    fun showLoading() {
        mList.add(null)
        cacheLoadingPos = mList.size - 1
        notifyItemInserted(cacheLoadingPos)
    }

    fun removeLoadingIfNeed() {
        if (cacheLoadingPos in 0..mList.size) {
            mList.removeAt(cacheLoadingPos)
        }
        notifyDataSetChanged()
    }

    fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition..toPosition - 1) {
                Collections.swap(mList, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(mList, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    fun onItemDismiss(position: Int) {
        mList.removeAt(position)
        notifyItemRemoved(position)
    }

    interface OnClickListener<T> {
        fun onClick(item: T?, position: Int)
    }

    class LoadingViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}
