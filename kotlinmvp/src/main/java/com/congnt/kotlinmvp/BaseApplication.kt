package com.congnt.kotlinmvp

import android.app.Application

/**
 * Created by congn on 3/21/2017.
 */
class BaseApplication : Application()//    public ApplicationComponent applicationComponent;
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        final File baseDir = getCacheDir();
//        File cacheDir = null;
//        if (baseDir != null) {
//            cacheDir = new File(baseDir, "HttpResponseCache");
//        }
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        applicationComponent = DaggerApplicationComponent.builder()
//                .applicationModule(new ApplicationModule(this, RetrofitBuilder.getRetrofit(
//                        AppConfig.IS_DEV ? AppConfig.BASE_URL_DEV : AppConfig.BASE_URL_STAGING,
//                        null,
//                        AppConfig.CONNECT_TIMEOUT,
//                        AppConfig.READ_TIMEOUT,
//                        new Cache(cacheDir, 10 * 1024 * 1024),
//                        interceptor,
//                        new CurlLoggingInterceptor()
//                )
//                )).build();
//    }
