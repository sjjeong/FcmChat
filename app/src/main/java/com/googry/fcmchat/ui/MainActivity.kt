package com.googry.fcmchat.ui

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.googry.fcmchat.R
import com.googry.fcmchat.ext.logE
import com.googry.fcmchat.ui.chat.ChatActivity
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        when (Build.MODEL) {
//            "SM-A520S" -> {
//                startActivity<ChatActivity>(ChatActivity.EXTRA_TO_KEY to "eo7CbxUJZS0:APA91bFly4UbuuUtyu2Bg-Aufy9Haz_g6FmN5-TNyFnhU9q6bWTDGljqq3jX7jhQNk_e5RsCblixxDuJX2Pmb0XISQjBjSWF5sLHGlPffTjc9UmgRTNU5ZsKVxqYxAtsItzQH1BNznub")
//            }
//            "Android SDK built for x86" -> {
//                startActivity<ChatActivity>(ChatActivity.EXTRA_TO_KEY to "cKVFx3oKu54:APA91bE-QBXbbizVCjsy6Fo2k-oaDzaoXKP_cnGYqQ8ST6LhmPJJ4MJneXUk_dy3eQkdyRQpMP-oOi9vp2Dsr5F4vJ-KtG8ghhkC4DaKE_cQG3b3V7W0NHSC-iCEU_j-ELvoFzsz1eVb")
//            }
            //
            "SM-A520S" -> {
                startActivity<ChatActivity>(ChatActivity.EXTRA_TO_KEY to "frOTYdBrrRo:APA91bGrLH2bgQgm0Mrbvn7pVcb9GvfnFlIQFkuG8uKfY4kQYbJT9bNNkkWrKfmN9KO8qkhBoWO_7BI1ZyBXJZDFeHAN0cGNvqU3YLSHVnhy2lhop87Ks2NDQeI7kh4C_jNfJsFdQxUW")
            }
            "SM-G955N" -> {
                startActivity<ChatActivity>(ChatActivity.EXTRA_TO_KEY to "cKVFx3oKu54:APA91bE-QBXbbizVCjsy6Fo2k-oaDzaoXKP_cnGYqQ8ST6LhmPJJ4MJneXUk_dy3eQkdyRQpMP-oOi9vp2Dsr5F4vJ-KtG8ghhkC4DaKE_cQG3b3V7W0NHSC-iCEU_j-ELvoFzsz1eVb")
            }

        }
        logE(Build.MODEL)

        GoogleApiAvailability.getInstance().makeGooglePlayServicesAvailable(this)
            .addOnSuccessListener {
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

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("googry", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token
                logE("fcm token $token")
            })

    }
}
