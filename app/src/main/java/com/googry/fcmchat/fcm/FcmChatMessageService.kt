package com.googry.fcmchat.fcm

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import com.googry.fcmchat.data.Cache
import com.googry.fcmchat.data.enums.FcmPushType
import com.googry.fcmchat.data.model.ChatItem
import com.googry.fcmchat.data.model.ChatRoom
import com.googry.fcmchat.data.source.ChatRoomDataSource
import com.googry.fcmchat.ext.fromJson
import com.googry.fcmchat.ext.logE
import com.googry.fcmchat.util.bus.RxBus
import com.googry.fcmchat.util.bus.model.ChatItemEvent
import org.koin.android.ext.android.inject

class FcmChatMessageService
    : FirebaseMessagingService() {

    private val chatRoomDataSource by inject<ChatRoomDataSource>()

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        remoteMessage?.let {
            val gson = Gson()
            logE(gson.toJson(remoteMessage.data))
            when (remoteMessage.data["push_type"]) {
                FcmPushType.CHAT_ITEM.pushType -> {
                    gson.fromJson<ChatItem>(remoteMessage.data["push_data"])?.let {
                        logE(gson.toJson(it))
                        RxBus.sendEvent(ChatItemEvent(it))
                    }
                }
                FcmPushType.ADD_USER.pushType -> {
                    gson.fromJson<ChatRoom>(remoteMessage.data["push_data"])?.let {
                        chatRoomDataSource.getChatRoomWithUserId(it.userId).value
                            ?: chatRoomDataSource.saveChatRoom(it)
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