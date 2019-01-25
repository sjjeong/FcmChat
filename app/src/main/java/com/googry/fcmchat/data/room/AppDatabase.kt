package com.googry.fcmchat.data.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.googry.fcmchat.data.model.ChatRoom
import com.googry.fcmchat.data.room.converter.DateTypeConverter
import com.googry.fcmchat.data.room.dao.ChatRoomDao

@Database(entities = arrayOf(ChatRoom::class), version = 1)
@TypeConverters(
    value = [
        DateTypeConverter::class]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun chatRoomDao(): ChatRoomDao
}
