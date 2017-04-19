package com.congnt.kotlinmvp.mvp.presenter

import android.os.Bundle
import com.congnt.kotlinmvp.mvp.view.EventsDelegate

/**
 * Created by congn on 4/15/2017.
 */
interface MainLifecycleDelegate : EventsDelegate {

    fun onCreate(savedInstanceState: Bundle? = null) {
    }

    fun onStart() {
    }

    fun onResume() {
    }

    fun onPause() {
    }

    fun onSaveInstanceState(outState: Bundle?) {
    }

    fun onStop() {
    }

    fun onLowMemory() {
    }

    fun onDestroy() {
    }
}