package com.googry.fcmchat

import android.app.Application
import com.googry.fcmchat.di.viewModelModule
import org.koin.android.ext.android.startKoin

class FcmChatApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, arrayListOf(viewModelModule))
    }
}