package com.googry.fcmchat.data.source

import android.arch.lifecycle.LiveData
import com.googry.fcmchat.data.model.ChatRoom
import com.googry.fcmchat.network.model.NetworkResponse
import io.reactivex.disposables.Disposable

interface ChatRoomDataSource {

    fun getChatRoomWithUserId(userId: String): LiveData<ChatRoom>

    fun saveChatRoom(chatRoom: ChatRoom)

    fun sendChatRoom(
        token: String,
        response: NetworkResponse<Unit>
    ): Disposable

    fun getAllChatRoom(): LiveData<List<ChatRoom>>
}