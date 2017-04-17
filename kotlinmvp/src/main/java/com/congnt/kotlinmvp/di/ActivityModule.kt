package com.congnt.kotlinmvp.di

import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import com.congnt.kotlinmvp.mvp.BaseActivity
import dagger.Module
import dagger.Provides

/**
 * Created by congn on 4/15/2017.
 */
@Module
class ActivityModule(private val activity: BaseActivity) {
    @Provides
    @ActivityScope
    fun baseActivity(): BaseActivity {
        return activity
    }

    @Provides
    @ActivityScope
    fun layoutInflater(): LayoutInflater {
        return activity.layoutInflater
    }

    @Provides
    @ActivityScope
    fun fragmentManager(): FragmentManager {
        return activity.supportFragmentManager
    }
}