package com.googry.fcmchat.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "chat_room")
data class ChatRoom(
    @PrimaryKey(autoGenerate = true)
    val dbId: Long = 0L,
    @ColumnInfo(name = "user_id")
    var userId: String,
    @ColumnInfo(name = "user_name")
    var userName: String = "",
    @ColumnInfo(name = "user_fcm_token")
    var userFcmToken: String,
    @ColumnInfo(name = "create_time")
    var createTime: Date = Date(),
    @ColumnInfo(name = "update_time")
    var updateTime: Date = Date()
)