package com.googry.fcmchat.data.source

import android.arch.lifecycle.LiveData
import com.googry.fcmchat.data.model.ChatRoom
import com.googry.fcmchat.data.room.dao.ChatRoomDao

class ChatRoomRepository(private val chatRoomDao: ChatRoomDao) : ChatRoomDataSource {

    override fun getChatRoomWithUserId(userId: String): LiveData<ChatRoom> {
        return chatRoomDao.getChatRoom(userId)
    }

    override fun saveChatRoom(chatRoom: ChatRoom) {
        chatRoomDao.insetChatRoom(chatRoom)
    }

    override fun getAllChatRoom(): LiveData<List<ChatRoom>> {
        return chatRoomDao.getAllChatRoom()
    }
}