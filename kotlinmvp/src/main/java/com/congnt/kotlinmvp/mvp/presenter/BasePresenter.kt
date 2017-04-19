package com.congnt.kotlinmvp.mvp.presenter

import com.congnt.kotlinmvp.mvp.view.MvpView


/**
 * Created by congn on 03/21/2017.
 */

abstract class BasePresenter<V : MvpView> {
    var view: V? = null
    fun attach(view: V) {
        this.view = view
    }
}
