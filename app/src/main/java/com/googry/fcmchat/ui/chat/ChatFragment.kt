package com.googry.fcmchat.ui.chat

import android.os.Bundle
import android.view.View
import com.android.databinding.library.baseAdapters.BR
import com.googry.fcmchat.R
import com.googry.fcmchat.base.ui.BaseFragment
import com.googry.fcmchat.base.ui.SimpleRecyclerView
import com.googry.fcmchat.data.model.ChatItem
import com.googry.fcmchat.databinding.ChatFragmentBinding
import com.googry.fcmchat.databinding.ChatItemBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ChatFragment : BaseFragment<ChatFragmentBinding>(R.layout.chat_fragment) {

    companion object {
        const val TO_KEY = "TO_KEY"

        fun newInstance(toKey: String) = ChatFragment().apply {
            arguments = Bundle().apply {
                putString(TO_KEY, toKey)
            }
        }
    }

    private val chatViewModel by viewModel<ChatViewModel> {
        parametersOf(
            arguments?.getString(TO_KEY) ?: ""
        )
    }

    private val chatAdapter by lazy {
        object : SimpleRecyclerView.Adapter<ChatItem, ChatItemBinding>(
            layoutRes = R.layout.chat_item,
            bindingVariableId = BR.chatItem
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