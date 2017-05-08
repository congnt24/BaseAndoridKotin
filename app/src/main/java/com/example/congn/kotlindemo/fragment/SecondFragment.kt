package com.example.congn.kotlindemo.fragment

import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.congnt.kotlinmvp.adapter.SimpleRecyclerAdapter
import com.congnt.kotlinmvp.mvp.BaseFragment
import com.example.congn.kotlindemo.R
import io.reactivex.Observable
import kotlinx.android.synthetic.main.second_fragment.*


/**
 * Created by congn on 4/16/2017.
 */
class SecondFragment : BaseFragment(R.layout.second_fragment) {
    override fun injector() {

    }

    lateinit var adapter: SimpleRecyclerAdapter

    var list = ArrayList<String?>()

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_submit.setOnClickListener { activity.awesomeNavigation!!.openFragment(R.id.content_frame, ThirdFragment(), "ThirdFragment") }
//        RxRecyclerView.scrollEvents(recycler).subscribe(Consumer {
//            if (recycler.canScrollVertically(1)) {
////
//            }
//        }, Consumer { /*error*/ })
        recycler.layoutManager = LinearLayoutManager(activity)
        adapter = SimpleRecyclerAdapter(activity, list)
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .map { it.toString() }
                .subscribe({ list.add(it) }, {}, { adapter.notifyDataSetChanged() })
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .map { it.toString() }
                .subscribe({ list.add(it) }, {}, { adapter.notifyDataSetChanged() })
        recycler.adapter = adapter
        adapter.setOnScrollListener(recycler, {
            Handler().postDelayed({
                Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                        .map { "" + (it * 2) }
                        .subscribe({ list.add(it) }, {}, {
                            adapter.removeLoadingIfNeed()
                        })
                it.restate()
            }, 2000)
        })
    }
}