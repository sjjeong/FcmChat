package com.googry.fcmchat.di

import com.googry.fcmchat.data.source.FcmChatDataSource
import com.googry.fcmchat.data.source.FcmChatRepository
import org.koin.dsl.module.module

val dataSourceModule = module {
    single<FcmChatDataSource> { FcmChatRepository(get())}
}