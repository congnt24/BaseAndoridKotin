package com.congnt.kotlinmvp.mvp

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.congnt.kotlinmvp.mvp.presenter.ExtraLifecycleDelegate
import com.congnt.kotlinmvp.mvp.presenter.MainLifecycleDelegate
import com.congnt.kotlinmvp.navigator.AwesomeNavigation
import com.congnt.kotlinmvp.utility.hideSoftKeyboard

/**
 * awesomeNavigation: to add/replace/pop fragment
 * Show/hide progress
 */
abstract class BaseActivity(open var layoutId: Int) : AppCompatActivity(), FragmentManager.OnBackStackChangedListener {
    var awesomeNavigation: AwesomeNavigation<BaseFragment>? = null
    private var mainLifecycleDelegates: Array<out MainLifecycleDelegate> = arrayOf()
    private var extraLifecycleDelegates: Array<out ExtraLifecycleDelegate> = arrayOf()
    protected open fun setMainLifecycleDelegates(vararg mainDelegates: MainLifecycleDelegate) {
        mainLifecycleDelegates = mainDelegates
    }

    protected open fun setExtraLifecycleDelegates(vararg extraDelegates: ExtraLifecycleDelegate) {
        extraLifecycleDelegates = extraDelegates
    }

    protected abstract fun initialize()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        awesomeNavigation = AwesomeNavigation<BaseFragment>(this)
        initialize()
    }

    override fun onStart() {
        super.onStart()
        mainLifecycleDelegates.forEach { it.onStart() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        extraLifecycleDelegates.forEach {
            it.onActivityResult(requestCode, resultCode, data)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onBackStackChanged() {
        hideSoftKeyboard(this)
    }
}
