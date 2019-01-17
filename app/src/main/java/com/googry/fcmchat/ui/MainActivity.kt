package com.googry.fcmchat.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.googry.fcmchat.R
import com.googry.fcmchat.ui.chat.ChatActivity
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity<ChatActivity>()
    }
}
