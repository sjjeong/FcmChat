package com.googry.fcmchat.vm

import android.arch.lifecycle.LiveData
import com.googry.fcmchat.base.ui.BaseViewModel
import com.googry.fcmchat.data.model.ChatRoom
import com.googry.fcmchat.data.source.ChatRoomDataSource

class HomeViewModel(private val chatRoomDataSource: ChatRoomDataSource) : BaseViewModel() {

    var liveAllChatRoom: LiveData<List<ChatRoom>> = chatRoomDataSource.getAllChatRoom()

    fun refresh() {
        liveAllChatRoom = chatRoomDataSource.getAllChatRoom()
    }

}