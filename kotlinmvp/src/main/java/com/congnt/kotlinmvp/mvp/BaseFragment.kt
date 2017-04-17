package com.congnt.kotlinmvp.mvp

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by NGUYEN TRUNG CONG on 08/13/2016
 */
abstract class BaseFragment(val layoutId: Int, var className: String?) : Fragment() {
    constructor(layoutId: Int) : this(layoutId, "")

    protected val activity: BaseActivity
        get() = getActivity() as BaseActivity

    protected abstract fun injector()

    override fun onCreate(savedInstanceState: Bundle?) {
        injector()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val inflate = inflater!!.inflate(layoutId, container, false)
        try {
            inflate.background.isVisible
        } catch (e: NullPointerException) {
            inflate.setBackgroundColor(Color.WHITE)
        }
        inflate.setOnTouchListener { _, _ -> true }
        return inflate
    }
}
