package com.congnt.kotlinmvp

import android.app.Application
import com.congnt.kotlinmvp.mvp.RxSharedPreferences

/**
 * Created by congn on 3/21/2017.
 */
open class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        RxSharedPreferences.initialize(this)
    }
}