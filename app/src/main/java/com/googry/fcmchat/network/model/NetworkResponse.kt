package com.googry.fcmchat.network.model

class NetworkResponse<T>(
    var success: ((response: T) -> Unit)? = null,
    var failed: ((errorCode: String) -> Unit)? = null
)