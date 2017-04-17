package com.congnt.kotlinmvp.mvp.domain


import io.reactivex.Observable
import retrofit2.Retrofit

/**
 * Created by congn on 3/23/2017.
 */

abstract class UseCase<T>(protected var retrofit: Retrofit) {

    abstract fun getObservable(vararg params: String): Observable<T>
}
