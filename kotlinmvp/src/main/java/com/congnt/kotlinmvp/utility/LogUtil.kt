package com.congnt.kotlinmvp.utility

import android.content.Context
import android.util.Log
import android.widget.Toast

/**
 * Created by congn on 4/19/2017.
 */

fun String.d(TAG: String): Int {
    return Log.d(TAG, this)
}

fun String.e(TAG: String): Int {
    return Log.e(TAG, this)
}

fun String.w(TAG: String): Int {
    return Log.w(TAG, this)
}

fun String.toast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}

fun String.toastLong(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_LONG).show()
}