package com.googry.fcmchat.vm

import android.arch.lifecycle.MutableLiveData
import com.googry.fcmchat.base.ui.BaseViewModel
import com.googry.fcmchat.data.model.ChatRoom
import com.googry.fcmchat.data.source.ChatRoomDataSource
import com.googry.fcmchat.ext.plusAssign
import com.googry.fcmchat.network.model.NetworkResponse

class AddPersonViewModel(private val chatRoomDataSource: ChatRoomDataSource) : BaseViewModel() {

    val liveTitle = MutableLiveData<String>()
    val liveContent = MutableLiveData<String>()
    val liveNo = MutableLiveData<String>()
    val liveYes = MutableLiveData<String>()

    var chatRoom: ChatRoom? = null

    fun setUserData(userData: String) {
        if (userData.isEmpty()) {
            return
        }

        chatRoom = userData.split("#").let {
            ChatRoom(userId = it[0], userFcmToken = it[1])
        }
        chatRoomDataSource.getChatRoomWithUserId(chatRoom?.userId!!).value?.let {
            liveTitle.value = "이미 등록된 사람"
            liveContent.value = "이미 등록된 사람 입니다."
            liveNo.value = null
            liveYes.value = "확인"
        } ?: let {
            liveTitle.value = "새로운 사람 추가"
            liveContent.value = "새로운 사람을 추가하시겠습니까?"
            liveNo.value = "아니요"
            liveYes.value = "네"
        }
    }

    fun saveChatRoom(response: NetworkResponse<Unit>) {
        chatRoom?.let {
            chatRoomDataSource.saveChatRoom(it)
            compositeDisposable += chatRoomDataSource.sendChatRoom(it.userFcmToken, response)
        }
    }

}