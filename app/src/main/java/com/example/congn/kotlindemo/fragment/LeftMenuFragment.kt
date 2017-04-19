package com.example.congn.kotlindemo.fragment

import android.os.Bundle
import android.view.View
import com.congnt.kotlinmvp.bus.RxBus
import com.congnt.kotlinmvp.mvp.BaseFragment
import com.congnt.kotlinmvp.navigator.AwesomeNavigation
import com.example.congn.kotlindemo.LeftMenuViewExtensionDelegate
import com.example.congn.kotlindemo.R
import kotlinx.android.synthetic.main.fragment_left_menu.*


/**
 * Created by congn on 4/16/2017.
 */
class LeftMenuFragment : BaseFragment(R.layout.fragment_left_menu), View.OnClickListener {
    var leftMenuDelegate: LeftMenuViewExtensionDelegate? = null
    override fun onClick(v: View?) {
        leftMenuDelegate!!.onHideDrawer()
        when (v) {
            left_btn1 -> {
                activity.awesomeNavigation!!.openFragment(R.id.content_frame, FirstFragment(), "FirstFragment", AwesomeNavigation.FragmentActionType.REPLACE, AwesomeNavigation.FragmentAnimationType.NONE)
            }
            left_btn2 -> {
                activity.awesomeNavigation!!.openFragment(R.id.content_frame, SecondFragment(), "SecondFragment", AwesomeNavigation.FragmentActionType.REPLACE, AwesomeNavigation.FragmentAnimationType.NONE)

            }
            left_btn3 -> {//post an event
                RxBus.post("AAAAAAAAAaa")
            }
        }

    }

    override fun injector() {
        //Inject something like dagger2
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textview.text = " Hello World"
        left_btn1.setOnClickListener(this)
        left_btn2.setOnClickListener(this)
        left_btn3.setOnClickListener(this)
    }
}