package com.googry.fcmchat

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.googry.fcmchat.di.viewModelModule
import io.fabric.sdk.android.Fabric
import org.koin.android.ext.android.startKoin

class FcmChatApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, arrayListOf(viewModelModule))


        if (BuildConfig.DEBUG) {
            Fabric.with(this, Crashlytics())
        }
    }
}