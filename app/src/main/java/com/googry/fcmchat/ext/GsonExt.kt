package com.googry.fcmchat.ext

import com.google.gson.Gson


inline fun <reified T> Gson.fromJson(jsonString: String?, default: T? = null): T? {
    if (jsonString == null) {
        return default
    }
    return fromJson(jsonString, T::class.java)
}