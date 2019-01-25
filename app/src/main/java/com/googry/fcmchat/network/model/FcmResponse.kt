package com.googry.fcmchat.network.model

import com.google.gson.annotations.SerializedName

data class FcmResponse(
    @SerializedName("canonical_ids")
    val canonicalIds: Int,
    @SerializedName("failure")
    val failure: Int,
    @SerializedName("multicast_id")
    val multicastId: Long,
    @SerializedName("results")
    val results: List<Map<String, String>>,
    @SerializedName("success")
    val success: Int
)