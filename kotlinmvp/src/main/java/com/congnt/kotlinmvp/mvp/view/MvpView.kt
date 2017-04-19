package com.congnt.kotlinmvp.mvp.view

import android.app.Activity
import android.view.View
import com.congnt.kotlinmvp.utility.hideSoftKeyboard
import com.congnt.kotlinmvp.utility.showSoftKeyboard

/**
 * Created by congn on 4/15/2017.
 */
//Is something a View would comply to
interface MvpView {
    fun hideLoading() {}
    fun showLoading() {}
    fun hideKeyboard(activity: Activity) {
        hideSoftKeyboard(activity)
    }

    fun showKeyboard(view: View) {
        showSoftKeyboard(view)
    }
}

//Is a contract for event, that ViewExtension can fire
interface EventsDelegate
