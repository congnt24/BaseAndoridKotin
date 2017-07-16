package com.congnt.kotlinmvp.bus

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlin.reflect.KClass

//object RxBus {
//    private val bus = PublishSubject.create<Any>()
//    fun post(event: Any) {
//        bus.onNext(event)
//    }
//
//    fun <T> events(type: Class<T>): Observable<T> {
//        return bus.ofType(type).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//    }
//
//    fun hasObservable(): Boolean {
//        return bus.hasObservers()
//    }
//}

object RxBus {
    private var mapBus: MutableMap<String, Any> = HashMap()

    fun <E: Any> events(type: KClass<E>) : Observable<E> {
        if (mapBus[type.javaObjectType.name] == null) {
            mapBus[type.javaObjectType.name] = PublishSubject.create<E>()
        }
        return (mapBus[type.javaObjectType.name] as PublishSubject<E>).ofType(type.javaObjectType).share()
    }

    fun <E: Any> post(event: E){
        (mapBus[event.javaClass.name] as PublishSubject<E>).onNext(event)
    }

    fun <E: Any> release(type: KClass<E>){
        mapBus.forEach{
            if (it.key.equals(type.javaObjectType.name)){
                (it.value as PublishSubject<Any>).onComplete()
                return
            }
        }
    }

    fun release(){
        mapBus.forEach { (it.value as PublishSubject<Any>).onComplete() }
    }
}