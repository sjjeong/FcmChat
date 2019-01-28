package com.googry.fcmchat.di

import com.googry.fcmchat.data.source.ChatRoomDataSource
import com.googry.fcmchat.data.source.ChatRoomRepository
import com.googry.fcmchat.data.source.FcmChatDataSource
import com.googry.fcmchat.data.source.FcmChatRepository
import org.koin.dsl.module.module

val dataSourceModule = module {
    single<FcmChatDataSource> { FcmChatRepository(get()) }
    single<ChatRoomDataSource> { ChatRoomRepository(get(), get()) }
}