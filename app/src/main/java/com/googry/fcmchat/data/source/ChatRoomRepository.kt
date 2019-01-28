package com.googry.fcmchat.data.source

import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import android.os.Build
import com.googry.fcmchat.data.Cache
import com.googry.fcmchat.data.enums.FcmPushType
import com.googry.fcmchat.data.model.ChatRoom
import com.googry.fcmchat.data.room.dao.ChatRoomDao
import com.googry.fcmchat.ext.networkCommunication
import com.googry.fcmchat.network.api.FirebaseApi
import com.googry.fcmchat.network.model.FcmData
import com.googry.fcmchat.network.model.NetworkResponse
import io.reactivex.disposables.Disposable

class ChatRoomRepository(
    private val chatRoomDao: ChatRoomDao,
    private val firebaseApi: FirebaseApi
) : ChatRoomDataSource {

    override fun getChatRoomWithUserId(userId: String): LiveData<ChatRoom> {
        return chatRoomDao.getChatRoom(userId)
    }

    override fun saveChatRoom(chatRoom: ChatRoom) {
        AsyncTask.execute {
            chatRoomDao.insetChatRoom(chatRoom)
        }
    }

    override fun sendChatRoom(token: String, response: NetworkResponse<Unit>): Disposable {
        val data = FcmData.Data(
            pushType = FcmPushType.ADD_USER.pushType,
            pushData = ChatRoom(userId = Build.MODEL, userFcmToken = Cache.fcmToken)
        )
        return firebaseApi.postSendFcm(FcmData(data = data, to = token))
            .networkCommunication()
            .subscribe({
                if (it.success == 1) {
                    response.success?.invoke(Unit)
                } else {
                    response.failed?.invoke(it.results[0]["error"] ?: "")
                }
            }) {
                response.failed?.invoke("")
            }
    }

    override fun getAllChatRoom(): LiveData<List<ChatRoom>> {
        return chatRoomDao.getAllChatRoom()
    }
}