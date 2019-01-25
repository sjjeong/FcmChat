package com.googry.fcmchat.util.bus.model

import com.googry.fcmchat.data.model.ChatItem

class ChatItemEvent(val chatItem: ChatItem) : BusEvent()