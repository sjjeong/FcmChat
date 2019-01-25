package com.googry.fcmchat.di

import android.arch.persistence.room.Room
import com.googry.fcmchat.data.room.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val ROOM_DB = "ROOM_DB"

val daoModule = module {
    single(ROOM_DB) {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java, "fcm_chat_db"
        ).build()
    }

    single { (get(ROOM_DB) as AppDatabase).chatRoomDao() }
}