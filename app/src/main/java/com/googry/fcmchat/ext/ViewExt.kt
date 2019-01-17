package com.googry.fcmchat.ext

import android.databinding.BindingAdapter
import android.view.View
import com.jakewharton.rxbinding2.view.RxView
import org.jetbrains.anko.backgroundResource
import java.util.concurrent.TimeUnit

@BindingAdapter(value = ["selected"])
fun View.setSelected(selected: Boolean) {
    isSelected = selected
}

@BindingAdapter(value = ["enabled"])
fun View.setEnabled(enabled: Boolean) {
    isEnabled = enabled
}

@BindingAdapter(value = ["onBlockClick"])
fun View.setOnBlockClick(listener: View.OnClickListener) {
    RxView.clicks(this)
        .throttleFirst(1000L, TimeUnit.MILLISECONDS)
        .subscribe {
            listener.onClick(this)
        }
}

@BindingAdapter(value = ["backgroundResId"])
fun View.setOnBackgroundResId(resId: Int) {
    backgroundResource = resId
}


/**
 * 점선은 따로 View에서 hardwareAccelerated를 false로 해야 해서 BindingAdapter로 선언
 */
@BindingAdapter(value = ["hardwareAccelerated"])
fun View.setHardwareAccelerated(enabled: Boolean) {
    setLayerType(if (enabled) View.LAYER_TYPE_HARDWARE else View.LAYER_TYPE_SOFTWARE, null)
}