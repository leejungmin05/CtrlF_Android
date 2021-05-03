package com.thinlineit.ctrlf.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView


@BindingAdapter("data")
fun <T> setRecyclerViewData(recyclerView: RecyclerView, data: T) {
    if (recyclerView.adapter is BindingRecyclerViewAdapter<*>)
        (recyclerView.adapter as BindingRecyclerViewAdapter<T>).setData(data)
}