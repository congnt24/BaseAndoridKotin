package com.example.congn.kotlindemo.fragment

import android.os.Bundle
import android.view.View
import com.congnt.kotlinmvp.mvp.BaseFragment
import com.example.congn.kotlindemo.R
import kotlinx.android.synthetic.main.fragment_left_menu.*

interface LeftMenuViewExtensionDelegate {
    fun onBtn1Click()
    fun onBtn2Click()
    fun onBtn3Click()
    fun onHideDrawer()
    fun onShowDrawer()
}

/**
 * Created by congn on 4/16/2017.
 */
class LeftMenuFragment : BaseFragment(R.layout.fragment_left_menu), View.OnClickListener {
    var leftMenuDelegate: LeftMenuViewExtensionDelegate? = null
    override fun onClick(v: View?) {
        leftMenuDelegate!!.onHideDrawer()
        when (v) {
            left_btn1 -> {
                leftMenuDelegate!!.onBtn1Click()
            }
            left_btn2 -> {
                leftMenuDelegate!!.onBtn2Click()
            }
            left_btn3 -> {
                leftMenuDelegate!!.onBtn3Click()
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