package com.googry.fcmchat.base.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.googry.fcmchat.R
import com.googry.fcmchat.ext.replaceFragmentInActivity

abstract class BaseFragmentActivity(private val fragment: Fragment? = null) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_fragment_activity)
        fragment?.let {
            replaceFragmentInActivity(fragment, R.id.fl_content)
        }
    }

}