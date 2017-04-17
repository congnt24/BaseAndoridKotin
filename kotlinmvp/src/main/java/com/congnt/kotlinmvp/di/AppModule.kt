package com.congnt.kotlinmvp.di

import android.content.Context
import android.content.res.Resources
import com.google.gson.Gson
import com.tbruyelle.rxpermissions.RxPermissions
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by congn on 4/15/2017.
 */
@Module
class AppModule (private val context: Context) {

    @Singleton
    @Provides
    fun getContext(): Context {
        return context
    }

    @Singleton
    @Provides
    fun getResource(): Resources {
        return context.resources
    }

    @Singleton
    @Provides
    fun getGson(): Gson {
        return Gson()
    }
}