package com.googry.fcmchat.fcm

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import com.googry.fcmchat.data.Cache
import com.googry.fcmchat.data.enums.FcmPushType.CHAT_ITEM
import com.googry.fcmchat.data.model.ChatItem
import com.googry.fcmchat.ext.fromJson
import com.googry.fcmchat.ext.logE
import com.googry.fcmchat.util.bus.RxBus
import com.googry.fcmchat.util.bus.model.ChatItemEvent

class FcmChatMessageService
    : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        remoteMessage?.let {
            val gson = Gson()
            logE(gson.toJson(remoteMessage.data))
            when (remoteMessage.data["push_type"]) {
                CHAT_ITEM.pushType -> {
                    gson.fromJson<ChatItem>(remoteMessage.data["push_data"])?.let {
                        logE(gson.toJson(it))
                        RxBus.sendEvent(ChatItemEvent(it))
                    }
                }
                else -> {
                    error("Known push type")
                }
            }
        }

    }

    override fun onNewToken(newToken: String?) {
        super.onNewToken(newToken)
        logE("new token $newToken")
        Cache.fcmToken = newToken ?: ""
    }
}