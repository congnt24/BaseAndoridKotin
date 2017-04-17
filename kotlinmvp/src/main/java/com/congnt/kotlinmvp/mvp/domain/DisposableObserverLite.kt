package com.congnt.kotlinmvp.mvp.domain

import io.reactivex.observers.DisposableObserver

/**
 * Created by congn on 3/23/2017.
 */

/**
 * This class is use for reduce the code for implementing by override onNext, onError Mothod, ignore  onComplete

 * @param <T>
</T> */
abstract class DisposableObserverLite<T> : DisposableObserver<T>() {
    abstract fun onSuccess(value: T)

    abstract fun onFailure(e: Throwable)

    override fun onNext(value: T) {
        onSuccess(value)
    }

    override fun onError(e: Throwable) {
        onFailure(e)
    }

    override fun onComplete() {

    }
}
