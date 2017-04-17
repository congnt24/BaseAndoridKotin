package com.congnt.kotlinmvp.mvp.view

/**
 * Created by congn on 4/15/2017.
 */
//Is something a View would comply to
interface ViewContract

//For Screen
interface ScreenContract : ViewContract

//For View Extension
interface ViewExtension

//Is a contract for event, that ViewExtension can fire
interface EventsDelegate

interface EventsDelegateViewExtension<D : EventsDelegate> : ViewExtension {
    var eventsDelegate: D?
}