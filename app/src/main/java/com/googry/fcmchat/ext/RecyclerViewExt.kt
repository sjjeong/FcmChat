package com.googry.fcmchat.ext

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.googry.fcmchat.base.ui.SimpleRecyclerView

@Suppress("UNCHECKED_CAST")
@BindingAdapter("replaceAll")
fun RecyclerView.replaceAll(list: List<Any>?) {
    (this.adapter as? SimpleRecyclerView.Adapter<Any, *>)?.run {
        replaceAll(list)
        notifyDataSetChanged()
    }
}
