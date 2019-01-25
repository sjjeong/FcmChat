package com.googry.fcmchat.ui.dialog

import android.os.Bundle
import android.view.View
import com.googry.fcmchat.R
import com.googry.fcmchat.base.ui.BaseDialogFragment
import com.googry.fcmchat.databinding.AddPersonDialogBinding
import com.googry.fcmchat.vm.AddPersonViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AddPersonDialog
    : BaseDialogFragment<AddPersonDialogBinding>(R.layout.add_person_dialog) {

    companion object {
        const val KEY_USER_DATA = "KEY_USER_DATA"

        fun newInstance(userData: String, yesClick: () -> Unit, noClick: (() -> Unit)? = null) =
            AddPersonDialog().apply {
                arguments = Bundle().apply {
                    putString(KEY_USER_DATA, userData)
                }
                this.yesClick = yesClick
                this.noClick = noClick
            }
    }

    var yesClick: (() -> Unit)? = null
    var noClick: (() -> Unit)? = null

    private val addPersonViewModel by viewModel<AddPersonViewModel> { parametersOf() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addPersonViewModel.setUserData(arguments?.getString(KEY_USER_DATA) ?: "")

        binding.run {
            addPersonVM = addPersonViewModel
            tvYes.setOnClickListener {
                addPersonViewModel.saveChatRoom()
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