package com.googry.fcmchat.ui.dialog

import android.os.Bundle
import android.view.View
import com.googry.fcmchat.R
import com.googry.fcmchat.base.ui.BaseDialogFragment
import com.googry.fcmchat.databinding.AddPersonDialogBinding

class AddPersonDialog
    : BaseDialogFragment<AddPersonDialogBinding>(R.layout.add_person_dialog) {

    companion object {
        const val KEY_FCM_TOKEY = "KEY_FCM_TOKEN"

        fun newInstance(fcmToken: String, yesClick: () -> Unit, noClick: (() -> Unit)? = null) =
            AddPersonDialog().apply {
                arguments = Bundle().apply {
                    putString(KEY_FCM_TOKEY, fcmToken)
                }
                this.yesClick = yesClick
                this.noClick = noClick
            }
    }

    var yesClick: (() -> Unit)? = null
    var noClick: (() -> Unit)? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            title = "새로운 사람 추가"
            content = "새로운 사람을 추가하시겠습니까?"
            no = "아니요"
            yes = "네"

            tvYes.setOnClickListener {
                dismissAllowingStateLoss()
                yesClick?.invoke()
            }
            tvNo.setOnClickListener {
                dismissAllowingStateLoss()
                noClick?.invoke()
            }
        }

    }
}