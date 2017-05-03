package com.congnt.kotlinmvp.utility

import android.content.Context
import android.util.Log
import android.widget.Toast

/**
 * Created by congn on 4/19/2017.
 */

 /**
 * "TAG".d("this is a debug log")
 */
fun String.d(str: String): Int {
    return Log.d(this, str)
}

 /**
 * "TAG".d("this is a error log")
 */
fun String.e(str: String): Int {
    return Log.e(this, str)
}

 /**
 * "TAG".d("this is a warning log")
 */
fun String.w(str: String): Int {
    return Log.w(this, str)
}

fun String.toast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}

fun String.toastLong(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_LONG).show()
}