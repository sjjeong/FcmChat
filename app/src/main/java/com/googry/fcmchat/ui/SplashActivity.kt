package com.googry.fcmchat.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.googry.fcmchat.data.Cache
import com.googry.fcmchat.ext.logE
import com.googry.fcmchat.ui.home.MainActivity
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GoogleApiAvailability.getInstance().makeGooglePlayServicesAvailable(this)
            .addOnSuccessListener {
                FirebaseInstanceId.getInstance().instanceId
                    .addOnCompleteListener(OnCompleteListener { task ->
                        if (!task.isSuccessful) {
                            Log.w("googry", "getInstanceId failed", task.exception)
                            return@OnCompleteListener
                        }

                        // Get new Instance ID token
                        val token = task.result?.token
                        logE("fcm token $token")
                        Cache.fcmToken = token ?: ""
                        startActivity<MainActivity>()
                    })
                logE("addOnSuccessListener")
            }
            .addOnFailureListener {
                logE("addOnFailureListener")
            }
            .addOnCompleteListener {
                logE("addOnCompleteListener")
            }
            .addOnCanceledListener {
                logE("addOnCanceledListener")
            }


    }
}