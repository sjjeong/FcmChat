package com.googry.fcmchat.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.android.databinding.library.baseAdapters.BR
import com.google.zxing.integration.android.IntentIntegrator
import com.googry.fcmchat.R
import com.googry.fcmchat.base.ui.BaseFragment
import com.googry.fcmchat.base.ui.SimpleRecyclerView
import com.googry.fcmchat.data.Cache
import com.googry.fcmchat.data.model.ChatRoom
import com.googry.fcmchat.databinding.ChatRoomItemBinding
import com.googry.fcmchat.databinding.HomeFragmentBinding
import com.googry.fcmchat.ext.logE
import com.googry.fcmchat.ui.chat.ChatActivity
import com.googry.fcmchat.ui.dialog.AddPersonDialog
import com.googry.fcmchat.ui.dialog.QrCodeDialog
import com.googry.fcmchat.vm.HomeViewModel
import com.googry.fcmchat.vm.navigator.HomeNavigator
import org.jetbrains.anko.support.v4.startActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class HomeFragment : BaseFragment<HomeFragmentBinding>(R.layout.home_fragment),
    HomeNavigator {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val homeVieWModel by viewModel<HomeViewModel> { parametersOf() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            homeVM = homeVieWModel
            homeNavigator = this@HomeFragment
            rvContent.adapter = object : SimpleRecyclerView.Adapter<ChatRoom, ChatRoomItemBinding>(
                layoutRes = R.layout.chat_room_item,
                bindingVariableId = BR.chatRoom
            ) {
                override fun onCreateViewHolder(
                    parent: ViewGroup,
                    viewType: Int
                ): SimpleRecyclerView.ViewHolder<ChatRoomItemBinding> {
                    return super.onCreateViewHolder(parent, viewType).apply {
                        itemView.setOnClickListener {
                            startActivity<ChatActivity>(ChatActivity.EXTRA_TO_KEY to binding.chatRoom?.userFcmToken)
                        }
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        logE("onActivityResult in Fragment")
        if (requestCode == IntentIntegrator.REQUEST_CODE) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result == null) {
                // 취소됨
                logE("Cancel")
            } else {
                // 스캔된 QRCode --> result.getContents()
                logE("Scanned: ${result.contents}")
                AddPersonDialog.newInstance(result.contents, yesClick = {
                    homeVieWModel.refresh()
                })
                    .show(childFragmentManager, "")
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }


        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun showAddPerson() {
        IntentIntegrator(activity).initiateScan()
    }

    override fun showQrCode() {
        QrCodeDialog.newInstance(Cache.fcmToken)
            .show(childFragmentManager, "")
    }
}