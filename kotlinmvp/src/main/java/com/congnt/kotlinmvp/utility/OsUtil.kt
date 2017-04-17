package com.congnt.kotlinmvp.utility

import android.app.Activity
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.PowerManager
import android.view.View
import android.view.inputmethod.InputMethodManager


//Keyboard

/**
 * Hides the soft keyboard
 */
fun hideSoftKeyboard(activity: Activity) {
    if (activity.currentFocus != null) {
        val inputMethodManager = activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(activity.currentFocus.windowToken, 0)
    }
}

/**
 * Shows the soft keyboard
 */
fun showSoftKeyboard(view: View) {
    val inputMethodManager = view.context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    view.requestFocus()
    inputMethodManager.showSoftInput(view, 0)
}

fun toggleKeyBoard(context: Context) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
}

private fun getPackageInfoByPackageName(context: Context, packageName: String): PackageInfo? {
    val pm = context.packageManager
    try {
        return pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
    } catch (e: Exception) {
        return null
    }
}

fun isAppInstalled(context: Context, packageName: String): Boolean {
    return getPackageInfoByPackageName(context, packageName) != null
}

fun getAppInstalledVersion(context: Context, packageName: String): Int {
    val pm = context.packageManager
    try {
        val infoApp = pm.getPackageInfo(packageName, 0)
        return infoApp.versionCode
    } catch (e: Exception) {
        return 0
    }
}

fun getAppVersion(context: Context): Int {
    try {
        val packageInfo = context.packageManager
                .getPackageInfo(context.packageName, 0)
        return packageInfo.versionCode
    } catch (e: PackageManager.NameNotFoundException) {
        // should never happen
        throw RuntimeException("Could not get package name: " + e)
    }

}

fun networkIsAvailable(context: Context): Boolean {
    var connected = false
    try {
        val connectivityManager = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkInfo = connectivityManager
                .activeNetworkInfo
        connected = networkInfo != null && networkInfo.isAvailable
                && networkInfo.isConnected
        return connected

    } catch (e: Exception) {
        android.util.Log.i("Fruitful", "Unable to detect nework connection")
    }

    return connected
}

fun lightUpScreen(context: Context) {
    val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager

    val isScreenOn = pm.isScreenOn

    if (isScreenOn == false) {
        val wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK or PowerManager.ACQUIRE_CAUSES_WAKEUP or PowerManager.ON_AFTER_RELEASE, "MyLock")
        wl.acquire(10000)
        val wl_cpu = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyCpuLock")
        wl_cpu.acquire(10000)
    }
}
