package com.congnt.kotlinmvp.mvp.view

import android.os.Bundle

/**
 * Created by congn on 4/15/2017.
 */
interface MainLifecycleDelegate {

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