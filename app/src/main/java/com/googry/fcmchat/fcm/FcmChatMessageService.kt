package com.googry.fcmchat.fcm

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import com.googry.fcmchat.ext.logE

class FcmChatMessageService
    :FirebaseMessagingService(){

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        remoteMessage?.let {
            logE(Gson().toJson(remoteMessage.data))
        }

    }

    override fun onNewToken(newToken: String?) {
        super.onNewToken(newToken)
    }
}