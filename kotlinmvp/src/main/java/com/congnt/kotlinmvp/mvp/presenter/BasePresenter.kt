package com.congnt.kotlinmvp.mvp.presenter

import com.congnt.kotlinmvp.mvp.view.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


/**
 * Created by congn on 03/21/2017.
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
