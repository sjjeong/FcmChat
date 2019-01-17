package com.googry.fcmchat.ui.chat

import android.arch.lifecycle.MutableLiveData
import com.googry.fcmchat.base.ui.BaseViewModel

class ChatViewModel
    : BaseViewModel() {
    val liveChatList = MutableLiveData<List<String>>().apply {
        value = mutableListOf()
    }

    val liveMessage = MutableLiveData<String>()

    fun sendChat() {
        liveChatList.value?.let {
            liveChatList.value = it.toMutableList().apply {
                add(0, liveMessage.value ?: "")
            }
            liveMessage.value = ""
        }

    }


}