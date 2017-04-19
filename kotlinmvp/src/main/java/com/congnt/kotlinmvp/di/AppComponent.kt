package com.congnt.kotlinmvp.di

import android.content.Context
import android.net.ConnectivityManager
import dagger.Component
import okhttp3.OkHttpClient
import javax.inject.Singleton

/**
 * Created by congn on 4/15/2017.
 */
@Singleton
@Component(modules = arrayOf(
        AppModule::class//==Appmodule.class
))
interface AppComponent {
    //here we list things that are of Singleton-scope and should be accessible by other scopes
    fun getContext(): Context
    fun getConnectivityManager(): ConnectivityManager
    fun getOkHttpClient(): OkHttpClient
}