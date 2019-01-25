package com.googry.fcmchat.di

import com.googry.fcmchat.BuildConfig
import com.googry.fcmchat.network.api.FirebaseApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            })
            .addInterceptor { chain ->
                chain.proceed(
                    chain.request()
                        .newBuilder()
                        .addHeader(
                            "Authorization",
                            "key=AAAAKTK-D-Y:APA91bEPtjVP8S4M0ehFo4FWwXwakUgeS6EDlkcuy3FvX99YzBlp7pN-ZULQGe5wGsY--SbjXpytgdXhaiJgUZXR0DmdSTBSbSXQ1dDnSrlRcHflIG1lb6EB24uqErlvUz-sWmhesi1z"
                        )
                        .build()
                )
            }
            .build()
    }

    single {
        GsonConverterFactory.create() as Converter.Factory
    }

    single {
        RxJava2CallAdapterFactory.create() as CallAdapter.Factory
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.FirebasePushUrl)
            .client(get())
            .addCallAdapterFactory(get())
            .addConverterFactory(get())
            .build()
            .create(FirebaseApi::class.java)
    }
}