package com.googry.fcmchat.network.api

import com.googry.fcmchat.network.model.FcmData
import com.googry.fcmchat.network.model.FcmResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Multipart
import retrofit2.http.POST

interface FirebaseApi {

    @POST("fcm/send")
    fun postSendFcm(@Body fcmData: FcmData): Single<FcmResponse>
}