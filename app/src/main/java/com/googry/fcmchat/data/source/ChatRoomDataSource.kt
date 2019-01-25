package com.googry.fcmchat.data.source

import android.arch.lifecycle.LiveData
import com.googry.fcmchat.data.model.ChatRoom

interface ChatRoomDataSource {

    fun getChatRoomWithUserId(userId: String): LiveData<ChatRoom>

    fun saveChatRoom(chatRoom: ChatRoom)

    fun getAllChatRoom(): LiveData<List<ChatRoom>>
}