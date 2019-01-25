package com.googry.fcmchat.network.api

import io.reactivex.Single
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface FirebaseApi {

    @POST("fcm/send/")
    @FormUrlEncoded
    fun postSendFcm(): Single<Unit>
}