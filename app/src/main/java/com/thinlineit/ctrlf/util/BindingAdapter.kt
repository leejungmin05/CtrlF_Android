package com.thinlineit.ctrlf.util

import android.view.View
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView


@BindingAdapter("app:data")
fun <T> setRecyclerViewData(recyclerView: RecyclerView, data: T) {
    if (recyclerView.adapter is BindingRecyclerViewAdapter<*>)
        (recyclerView.adapter as BindingRecyclerViewAdapter<T>).setData(data)
}

@BindingAdapter("app:onFocusLost")
fun EditText.onFocusLost(callback: () -> Unit) {
    setOnFocusChangeListener { v, hasFocus ->
        if(!hasFocus ) callback.invoke()
    }
}

