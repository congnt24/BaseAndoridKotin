package com.congnt.kotlinmvp.mvp

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.congnt.kotlinmvp.utility.hideSoftKeyboard

/**
 * Extends BaseFragment when you use AwesomeNavigation. It will auto generate clickable and white background.
 * So you can't click through fragments.
 * Auto hide keyboard when you click to the background
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
        inflate.setOnClickListener { hideSoftKeyboard(getActivity()) }
        return inflate
    }
}
