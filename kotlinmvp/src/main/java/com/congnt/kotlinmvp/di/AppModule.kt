package com.congnt.kotlinmvp.di

import android.content.Context
import android.content.res.Resources
import android.location.LocationManager
import android.net.ConnectivityManager
import com.congnt.kotlinmvp.utility.getOkHttpClient
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
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
    fun getLocationManager(): LocationManager {
        return context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    @Singleton
    @Provides
    fun getConectivityManager(): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}