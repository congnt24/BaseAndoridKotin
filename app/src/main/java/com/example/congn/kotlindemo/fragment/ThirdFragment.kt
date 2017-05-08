package com.example.congn.kotlindemo.fragment

import android.os.Bundle
import android.view.View
import com.congnt.kotlinmvp.mvp.BaseFragment
import com.example.congn.kotlindemo.R
import kotlinx.android.synthetic.main.first_fragment.*

/**
 * Created by congn on 4/16/2017.
 */
class ThirdFragment : BaseFragment(R.layout.third_fragment){
    override fun injector() {

    }
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_submit.setOnClickListener { activity.awesomeNavigation!!.clearAll() }
    }
}