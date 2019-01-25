package com.googry.fcmchat.ui.chat

import android.os.Bundle
import com.googry.fcmchat.R
import com.googry.fcmchat.base.ui.BaseFragmentActivity
import com.googry.fcmchat.ext.replaceFragmentInActivity

class ChatActivity : BaseFragmentActivity() {
    companion object {
        const val EXTRA_TO_KEY = "EXTRA_TO_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        replaceFragmentInActivity(
            ChatFragment.newInstance(intent.getStringExtra(EXTRA_TO_KEY)),
            R.id.fl_content
        )
    }
}