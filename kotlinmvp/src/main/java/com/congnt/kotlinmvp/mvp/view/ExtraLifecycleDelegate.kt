package com.congnt.kotlinmvp.mvp.view

import android.content.Intent
import android.view.KeyEvent

/**
 * Created by congn on 4/15/2017.
 */
interface ExtraLifecycleDelegate {
    fun onBackPressed(): Boolean {
        return false
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

    }

    fun onNewIntent(intent: Intent?) {

    }

    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

    }

    fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        return false
    }
}