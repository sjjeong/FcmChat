package com.googry.fcmchat.ui.chat

import android.os.Bundle
import android.view.View
import com.android.databinding.library.baseAdapters.BR
import com.googry.fcmchat.R
import com.googry.fcmchat.base.ui.BaseFragment
import com.googry.fcmchat.base.ui.SimpleRecyclerView
import com.googry.fcmchat.databinding.ChatFragmentBinding
import com.googry.fcmchat.databinding.ChatItemBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ChatFragment : BaseFragment<ChatFragmentBinding>(R.layout.chat_fragment) {

    companion object {
        fun newInstance() = ChatFragment()
    }

    private val chatViewModel by viewModel<ChatViewModel>()

    private val chatAdapter by lazy {
        object : SimpleRecyclerView.Adapter<String, ChatItemBinding>(
            layoutRes = R.layout.chat_item,
            bindingVariableId = BR.chat
        ) {}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            chatVM = chatViewModel
            rvContent.adapter = chatAdapter
        }
    }
}