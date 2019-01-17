package com.googry.fcmchat.ext

import android.databinding.BindingAdapter
import android.widget.ProgressBar
import com.googry.fcmchat.util.ProgressBarAnimation

@BindingAdapter(value = ["pbMax", "pbProgress"])
fun ProgressBar.setValue(pbMax: Int, pbProgress: Int) {
    max = pbMax
    startAnimation(ProgressBarAnimation(this, 0, pbProgress))
}