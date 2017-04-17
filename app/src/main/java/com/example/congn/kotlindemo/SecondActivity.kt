package com.example.congn.kotlindemo

import com.congnt.kotlinmvp.mvp.BaseActivity
import com.congnt.kotlinmvp.navigator.AwesomeNavigation
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.layout_activity_second.*

class SecondActivity : BaseActivity(R.layout.activity_second), LeftMenuViewExtensionDelegate {

    override fun onBtn1Click() {
        awesomeNavigation!!.openFragment(R.id.content_frame, FirstFragment(), "FirstFragment", AwesomeNavigation.FragmentActionType.REPLACE, AwesomeNavigation.FragmentAnimationType.NONE)
    }

    override fun onBtn2Click() {
        awesomeNavigation!!.openFragment(R.id.content_frame, SecondFragment(), "SecondFragment", AwesomeNavigation.FragmentActionType.REPLACE, AwesomeNavigation.FragmentAnimationType.NONE)
    }

    override fun onBtn3Click() {
        awesomeNavigation!!.openFragment(R.id.content_frame, ThirdFragment(), "ThirdFragment", AwesomeNavigation.FragmentActionType.REPLACE, AwesomeNavigation.FragmentAnimationType.NONE)
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
    }
}
