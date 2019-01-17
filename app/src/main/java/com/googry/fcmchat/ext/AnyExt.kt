package com.googry.fcmchat.ext

import com.google.gson.Gson

inline fun Any.toJsonString() = Gson().toJson(this)