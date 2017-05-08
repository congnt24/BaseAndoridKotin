package com.congnt.kotlinmvp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

/**
 * Created by congn_000 on 9/14/2016.
 */
class SimpleRecyclerAdapter(context: Context, mList: MutableList<String?>)
    : BaseSimpleRecyclerAdapter<String>(context, mList) {

    override val itemLayoutId: Int
        get() = android.R.layout.simple_list_item_1

    override fun getViewHolder(itemView: View): ViewHolder {
        return ViewHolder(itemView)
    }

    override fun bindHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = mList[position]
        if (item!=null && !item!!.isEmpty()) {
            if (holder is ViewHolder) holder.bind(item)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(str: String) {
            var text1 = itemView.findViewById(android.R.id.text1) as TextView
            text1.text = str
        }
    }
}
