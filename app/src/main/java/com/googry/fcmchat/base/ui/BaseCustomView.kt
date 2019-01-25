package com.googry.fcmchat.base.ui

import android.annotation.SuppressLint
import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.content.res.TypedArray
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout

abstract class BaseCustomView<B : ViewDataBinding>
    : FrameLayout {

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
        getAttrs(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView()
        getAttrs(attrs, defStyleAttr)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        initView()
        getAttrs(attrs, defStyleAttr, defStyleRes)
    }

    @SuppressLint("CustomViewStyleable")
    private fun getAttrs(attrs: AttributeSet?) {
        setTypeArray(context.obtainStyledAttributes(attrs, getCustomViewStyle()))
    }

    @SuppressLint("CustomViewStyleable")
    private fun getAttrs(attrs: AttributeSet?, defStyle: Int, defStyleRes: Int = 0) {
        setTypeArray(
            context.obtainStyledAttributes(
                attrs,
                getCustomViewStyle(),
                defStyle,
                defStyleRes
            )
        )
    }

    private fun initView() {
        binding = DataBindingUtil.bind(LayoutInflater.from(context)
            .inflate(getLayoutId(), this@BaseCustomView, false).apply {
                addView(this)
            })!!
    }

    abstract fun setTypeArray(typedArray: TypedArray)

    abstract fun getLayoutId(): Int

    abstract fun getCustomViewStyle(): IntArray

    protected lateinit var binding: B

    fun setLifecycleOwnerToDataBinding(owner: LifecycleOwner) {
        binding.setLifecycleOwner(owner)
    }


}