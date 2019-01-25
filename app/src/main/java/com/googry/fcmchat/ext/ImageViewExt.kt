package com.googry.fcmchat.ext

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.googry.fcmchat.util.QrCodeGenerator

@BindingAdapter(value = ["loadUrl"])
fun ImageView.loadUrl(url: String?) {
    url?.let {
        Glide.with(this)
            .load(it)
            .into(this)
    }
}

@BindingAdapter(value = ["drawableResId"])
fun ImageView.setDrawableResId(resId: Int) {
    setImageResource(resId)
}


@BindingAdapter(value = ["qrCode"])
fun ImageView.setQrCodeUrl(url: String) {
    Glide.with(this)
        .load(QrCodeGenerator.generate(url))
        .into(this)
}