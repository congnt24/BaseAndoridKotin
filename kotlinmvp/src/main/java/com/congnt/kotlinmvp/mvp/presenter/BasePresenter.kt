package com.congnt.kotlinmvp.mvp.presenter

import com.congnt.kotlinmvp.mvp.view.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


/**
 * Extend this class to have some base function suck as: attack and release view, add/clear subscription
 */
abstract class BasePresenter<V : MvpView> {
    val disposables: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    var view: V? = null
    fun attach(view: V) {
        this.view = view
    }

    fun release() {
        disposables.clear()
        view = null
    }

    protected fun addSubscription(subscription: Disposable) {
        disposables.add(subscription)
    }

    protected fun clearSubscriptions() {
        disposables.clear()
    }
}
