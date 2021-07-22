package com.thinlineit.ctrlf.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mukesh.MarkdownView


@BindingAdapter("data")
fun <T> setRecyclerViewData(recyclerView: RecyclerView, data: T) {
    if (recyclerView.adapter is BindingRecyclerViewAdapter<*>){
        if(data != null){
            (recyclerView.adapter as BindingRecyclerViewAdapter<T>).setData(data)
        }
    }
}

@BindingAdapter("markdownString")
fun setMarkdownString(markdownView: MarkdownView, string: String?) {
    string?.let {
        markdownView.setMarkDownText(it)
    }
}
