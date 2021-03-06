package com.googry.fcmchat.network.model

import com.google.gson.annotations.SerializedName

data class FcmData(
    @SerializedName("data")
    val data: Data,
    @SerializedName("to")
    val to: String
) {
    data class Data(
        @SerializedName("push_type")
        val pushType: String,
        @SerializedName("push_data")
        val pushData: Any
    )
}