package com.googry.fcmchat.ui.home

import android.os.Bundle
import android.view.View
import com.googry.fcmchat.R
import com.googry.fcmchat.base.ui.BaseFragment
import com.googry.fcmchat.data.Cache
import com.googry.fcmchat.databinding.HomeFragmentBinding
import com.googry.fcmchat.ui.dialog.QrCodeDialog
import com.googry.fcmchat.vm.HomeViewModel
import com.googry.fcmchat.vm.navigator.HomeNavigator
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
        }
    }


    override fun showAddPerson() {

    }

    override fun showQrCode() {
        QrCodeDialog.newInstance(Cache.fcmToken)
            .show(childFragmentManager, "")
    }
}