package com.googry.fcmchat.data.room.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.googry.fcmchat.data.model.ChatRoom

@Dao
interface ChatRoomDao {

    @Insert
    fun insetChatRoom(chatRoom: ChatRoom)

    @Query("SELECT * FROM chat_room WHERE user_id = :userId")
    fun getChatRoom(userId: String): LiveData<ChatRoom>

    @Query("SELECT * FROM chat_room")
    fun getAllChatRoom(): LiveData<List<ChatRoom>>

}