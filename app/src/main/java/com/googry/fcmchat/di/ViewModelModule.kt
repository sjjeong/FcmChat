package com.googry.fcmchat.di

import com.googry.fcmchat.ui.chat.ChatViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel { (to: String) -> ChatViewModel(get(), to) }
}