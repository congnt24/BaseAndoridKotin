package com.congnt.kotlinmvp.utility

import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by congn on 4/15/2017.
 */

fun getOkHttpClient(headerMap: Map<String, String>? = null, connectTimeoutInMs: Int? = 1000, readTimeoutInMs: Int? = 1000, cache: Cache?, vararg interceptors: Interceptor?): OkHttpClient {
    val httpClient = OkHttpClient.Builder()
    httpClient.connectTimeout(connectTimeoutInMs!!.toLong(), TimeUnit.MILLISECONDS)
    httpClient.readTimeout(readTimeoutInMs!!.toLong(), TimeUnit.MILLISECONDS)
    httpClient.addInterceptor {
        val original = it.request()
        val builder = original.newBuilder()
        if (headerMap != null) {
            for (item in headerMap.keys) {
                builder.header(item, headerMap[item])
            }
        }
        val request = builder.method(original.method(), original.body())
                .build()
        it.proceed(request)
    }

    for (interceptor in interceptors) {
        httpClient.addInterceptor(interceptor)
    }

    httpClient.cache(cache)
    return httpClient.build()
}
fun getRetrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
    var instance = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    return instance
}