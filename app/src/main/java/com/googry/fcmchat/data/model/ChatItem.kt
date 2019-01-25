package com.googry.fcmchat.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class ChatItem(
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("time")
    val time: Date = Date()
) {
    @Transient
    var isMyMessage: Boolean = true
}