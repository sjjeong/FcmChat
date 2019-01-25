package com.googry.fcmchat.data.source

import com.googry.fcmchat.data.enums.FcmPushType
import com.googry.fcmchat.data.model.ChatItem
import com.googry.fcmchat.ext.networkCommunication
import com.googry.fcmchat.network.api.FirebaseApi
import com.googry.fcmchat.network.model.FcmData
import com.googry.fcmchat.network.model.NetworkResponse
import io.reactivex.disposables.Disposable

class FcmChatRepository(val firebaseApi: FirebaseApi) : FcmChatDataSource {

    override fun sendChatItem(
        chatItem: ChatItem,
        to: String,
        response: NetworkResponse<Unit>
    ): Disposable {
        val data = FcmData.Data(
            pushType = FcmPushType.CHAT_ITEM.pushType,
            pushData = chatItem
        )
        return firebaseApi.postSendFcm(FcmData(data = data, to = to))
            .networkCommunication()
            .subscribe({
                if (it.success == 1) {
                    response.success?.invoke(Unit)
                }else{
                    response.failed?.invoke(it.results[0]["error"] ?: "")
                }
            }) {
                response.failed?.invoke("")
            }
    }
}