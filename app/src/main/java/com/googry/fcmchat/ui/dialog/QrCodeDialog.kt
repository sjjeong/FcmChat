package com.googry.fcmchat.ui.dialog

import android.os.Build
import android.os.Bundle
import android.view.View
import com.googry.fcmchat.R
import com.googry.fcmchat.base.ui.BaseDialogFragment
import com.googry.fcmchat.databinding.QrCodeDialogBinding

class QrCodeDialog : BaseDialogFragment<QrCodeDialogBinding>(R.layout.qr_code_dialog) {

    companion object {
        const val KEY_URL = "KEY_URL"

        fun newInstance(url: String) = QrCodeDialog().apply {
            arguments = Bundle().apply {
                putString(KEY_URL, url)
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            url = "${Build.MODEL}#${arguments?.getString(KEY_URL)}"
        }
    }
}