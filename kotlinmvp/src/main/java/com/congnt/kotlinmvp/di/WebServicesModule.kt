package com.congnt.kotlinmvp.di

import android.content.Context
import com.congnt.kotlinmvp.utility.CurlLoggingInterceptor
import com.congnt.kotlinmvp.utility.getOkHttpClient
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Singleton


/**
 * Created by congn on 4/15/2017.
 */
@Module
class WebServicesModule(private val context: Context) {

    @Singleton
    @Provides
    fun getOkHttp(): OkHttpClient {
        val baseDir = context.getCacheDir()
        var cacheDir: File? = null
        if (baseDir != null) {
            cacheDir = File(baseDir, "HttpResponseCache")
        }
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return getOkHttpClient(null, 1000, 1000, Cache(cacheDir, 10 * 1024 * 1024),
                interceptor, CurlLoggingInterceptor())
    }

    fun <T> createRetrofit(type: Class<T>, baseUrl: String, client: OkHttpClient): T {
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return retrofit.create(type)
    }

}