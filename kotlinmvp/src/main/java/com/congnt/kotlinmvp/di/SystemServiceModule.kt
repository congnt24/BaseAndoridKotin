package com.congnt.kotlinmvp.di

import android.content.Context
import android.content.SharedPreferences
import android.location.LocationManager
import android.net.ConnectivityManager
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by congn on 4/15/2017.
 */
@Module
class SystemServiceModule(private val context: Context) {
    @Singleton
    @Provides
    fun getPrefs(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
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