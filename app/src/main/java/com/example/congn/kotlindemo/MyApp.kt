package com.example.congn.kotlindemo

import com.congnt.kotlinmvp.BaseApplication
import com.congnt.kotlinmvp.di.AppComponent
import com.congnt.kotlinmvp.di.AppModule
import com.congnt.kotlinmvp.di.DaggerAppComponent

/**
 * Created by congn on 4/18/2017.
 */

class MyApp : BaseApplication() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}
