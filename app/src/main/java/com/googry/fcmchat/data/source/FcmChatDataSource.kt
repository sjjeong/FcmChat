package com.googry.fcmchat.data.source

import com.googry.fcmchat.data.model.ChatItem
import com.googry.fcmchat.network.model.NetworkResponse
import io.reactivex.disposables.Disposable

interface FcmChatDataSource {

    fun sendChatItem(
        chatItem: ChatItem,
        to: String,
        response: NetworkResponse<Unit>): Disposable
}