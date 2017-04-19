package com.congnt.kotlinmvp.di

import dagger.Component

/**
 * Created by congn on 4/15/2017.
 */
@ActivityScope
@Component(modules = arrayOf(ActivityModule::class), dependencies = arrayOf(AppComponent::class))
interface ActivityComponent {
    //Inject activity here
}