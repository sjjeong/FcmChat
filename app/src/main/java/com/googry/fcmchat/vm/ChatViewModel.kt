package com.googry.fcmchat.vm

import android.arch.lifecycle.MutableLiveData
import android.os.Build
import com.googry.fcmchat.base.ui.BaseViewModel
import com.googry.fcmchat.data.model.ChatItem
import com.googry.fcmchat.data.source.FcmChatDataSource
import com.googry.fcmchat.ext.plusAssign
import com.googry.fcmchat.network.model.NetworkResponse
import com.googry.fcmchat.util.bus.RxBus
import com.googry.fcmchat.util.bus.model.ChatItemEvent
import io.reactivex.android.schedulers.AndroidSchedulers

class ChatViewModel(
    private val fcmChatDataSource: FcmChatDataSource,
    private val toKey: String
) : BaseViewModel() {
    val liveChatList = MutableLiveData<List<ChatItem>>().apply {
        value = mutableListOf()
    }

    val liveMessage = MutableLiveData<String>()

    init {
        compositeDisposable += RxBus.bus.filter { it is ChatItemEvent }
            .map { it as ChatItemEvent }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                it.chatItem.isMyMessage = false
                liveChatList.value = liveChatList.value?.toMutableList()?.apply {
                    add(0, it.chatItem)
                }
            }
    }

    fun sendChat() {
        liveMessage.value?.let { message ->
            val chatItem = ChatItem(userId = "${Build.MODEL}", message = message)
            compositeDisposable += fcmChatDataSource.sendChatItem(chatItem, toKey, NetworkResponse(
                success = {
                    liveMessage.value = ""
                    liveChatList.value = liveChatList.value?.toMutableList()?.apply {
                        add(0, chatItem)
                    }
                },
                failed = {

                }
            ))
        }
    }


}