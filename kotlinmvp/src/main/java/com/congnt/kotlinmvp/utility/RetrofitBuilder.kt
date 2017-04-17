package com.congnt.kotlinmvp.utility

import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by congn_000 on 8/22/2016.
 */

fun createRetrofit(baseUrl: String, headerMap: Map<String, String>?, connectTimeoutInMs: Int, readTimeoutInMs: Int, cache: Cache?, interceptors: Array<Interceptor>?): Retrofit {
    val httpClient = OkHttpClient.Builder()
    if (connectTimeoutInMs > 0) {
        httpClient.connectTimeout(connectTimeoutInMs.toLong(), TimeUnit.MILLISECONDS)
    }
    if (connectTimeoutInMs > 0) {
        httpClient.readTimeout(readTimeoutInMs.toLong(), TimeUnit.MILLISECONDS)
    }
    httpClient.addInterceptor { chain ->
        val original = chain.request()
        val builder = original.newBuilder()
        if (headerMap != null) {
            for (item in headerMap.keys) {
                builder.header(item, headerMap[item])
            }
        }
        val request = builder.method(original.method(), original.body())
                .build()
        chain.proceed(request)
    }
    if (interceptors != null) {
        for (interceptor in interceptors) {
            httpClient.addInterceptor(interceptor)
        }
    }
    if (cache != null) {
        httpClient.cache(cache)
    }
    return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
            .build()
}
