package com.congnt.kotlinmvp.bus

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

object RxBus {
    private val bus = PublishSubject.create<Any>()
    fun post(event: Any) {
        bus.onNext(event)
    }

    fun <T> events(type: Class<T>): Observable<T> {
        return bus.ofType(type).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun hasObservable(): Boolean {
        return bus.hasObservers()
    }
}
