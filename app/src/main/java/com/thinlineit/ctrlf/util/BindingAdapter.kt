package com.thinlineit.ctrlf.util

import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.mukesh.MarkdownView

@BindingAdapter("app:data")
fun <T> setRecyclerViewData(recyclerView: RecyclerView, data: T) {
    if (recyclerView.adapter is BindingRecyclerViewAdapter<*> && data != null){
            (recyclerView.adapter as BindingRecyclerViewAdapter<T>).setData(data)
    }
}

@BindingAdapter("app:fragmentData")
fun <T> setFragmentStateData(viewPager: ViewPager2, data: T) {
    if(viewPager.adapter is BindingFragmentStateAdapter<*> && data != null)
        (viewPager.adapter as BindingFragmentStateAdapter<T>).setData(data)
}

@BindingAdapter("app:onFocusLost")
fun EditText.onFocusLost(callback: () -> Unit) {
    setOnFocusChangeListener { v, hasFocus ->
        if(!hasFocus ) callback.invoke()
    }
}

@BindingAdapter("markdownString")
fun setMarkdownString(markdownView: MarkdownView, string: String?) {
    string?.let {
        markdownView.setMarkDownText(it)
    }
}