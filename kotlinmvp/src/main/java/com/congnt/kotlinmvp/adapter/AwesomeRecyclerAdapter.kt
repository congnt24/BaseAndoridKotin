package com.congnt.kotlinmvp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import java.util.Collections

/**
 * Created by congn_000 on 9/14/2016.
 */
abstract class AwesomeRecyclerAdapter<VH : RecyclerView.ViewHolder, T>(context: Context, protected var mList: MutableList<T>, protected var onClickListener: AwesomeRecyclerAdapter.OnClickListener<T>?) : RecyclerView.Adapter<VH>() {
    private val mLayoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val itemView = mLayoutInflater.inflate(itemLayoutId, parent, false)
        return getViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        bindHolder(holder, position)
        holder.itemView.setOnClickListener {
            if (onClickListener != null)
                onClickListener!!.onClick(mList[position], position)
        }
        holder.itemView.setOnLongClickListener { onItemLongClick(holder, mList[position], position) }
    }


    override fun getItemCount(): Int {
        return mList.size
    }

    protected abstract val itemLayoutId: Int

    protected abstract fun getViewHolder(itemView: View): VH

    protected abstract fun bindHolder(holder: VH, position: Int)

    protected fun onItemLongClick(holder: VH, t: T, position: Int): Boolean {
        return false
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
        fun onClick(item: T, position: Int)
    }
}
