package com.example.congn.kotlindemo

import com.congnt.kotlinmvp.bus.RxBus
import com.congnt.kotlinmvp.mvp.BaseActivity
import com.congnt.kotlinmvp.navigator.AwesomeNavigation
import com.example.congn.kotlindemo.fragment.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_activity_second.*

class MainActivity : BaseActivity(R.layout.activity_main), LeftMenuViewExtensionDelegate {

    override fun onBtn1Click() {
        awesomeNavigation!!.openFragment(R.id.content_frame, FirstFragment(), "FirstFragment", AwesomeNavigation.FragmentActionType.REPLACE, AwesomeNavigation.FragmentAnimationType.NONE)
    }

    override fun onBtn2Click() {
        awesomeNavigation!!.openFragment(R.id.content_frame, SecondFragment(), "SecondFragment", AwesomeNavigation.FragmentActionType.REPLACE, AwesomeNavigation.FragmentAnimationType.NONE)
    }

    override fun onBtn3Click() {
//        awesomeNavigation!!.openFragment(R.id.content_frame, ThirdFragment(), "ThirdFragment", AwesomeNavigation.FragmentActionType.REPLACE, AwesomeNavigation.FragmentAnimationType.NONE)
        RxBus.post("AAAAAAAAAaa")
    }

    override fun onHideDrawer() {
        drawer_layout.closeDrawer(drawer.view)
    }

    override fun onShowDrawer() {
        drawer_layout.openDrawer(drawer.view)
    }

    val drawer: LeftMenuFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.left_drawer) as LeftMenuFragment
    }

    override fun initialize() {
        drawer.leftMenuDelegate = this
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        btn_showdrawer.setOnClickListener { onShowDrawer() }
        RxBus.events(String::class.java)
                .subscribe { tv_result.text = it }
    }
}
