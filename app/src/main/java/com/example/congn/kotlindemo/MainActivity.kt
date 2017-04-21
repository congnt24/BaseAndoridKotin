package com.example.congn.kotlindemo

import android.util.Log
import com.congnt.kotlinmvp.bus.RxBus
import com.congnt.kotlinmvp.mvp.BaseActivity
import com.example.congn.kotlindemo.fragment.LeftMenuFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_activity_second.*

interface LeftMenuViewExtensionDelegate {
    fun onHideDrawer()
    fun onShowDrawer()
}
class MainActivity : BaseActivity(R.layout.activity_main), LeftMenuViewExtensionDelegate {

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
        MyPrefs.isFirst.save(true)
        MyPrefs.test.save(User("Cong"))
        Log.d("TAG", "AAAAAAAAAA " + MyPrefs.isFirst.load(defaultT = false))
        Log.d("TAG", "AAAAAAAAAA " + MyPrefs.test.load())
        Log.d("TAG", "AAAAAAAAAA Context is null:? " + MyApp.appComponent.getOkHttpClient().connectTimeoutMillis())


    }
}
