package com.thinlineit.ctrlf.util

import android.view.View
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mukesh.MarkdownView


@BindingAdapter("app:data")
fun <T> setRecyclerViewData(recyclerView: RecyclerView, data: T) {
    if (recyclerView.adapter is BindingRecyclerViewAdapter<*>)
        (recyclerView.adapter as BindingRecyclerViewAdapter<T>).setData(data)
}

<<<<<<< HEAD:app/src/main/java/com/thinlineit/ctrlf/util/BindingAdapter.kt
@BindingAdapter("app:onFocusLost")
fun EditText.onFocusLost(callback: () -> Unit) {
    setOnFocusChangeListener { v, hasFocus ->
        if(!hasFocus ) callback.invoke()
    }
}

=======
@BindingAdapter("markdownString")
fun setMarkdownString(markdownView: MarkdownView, string: String?) {
    string?.let {
        markdownView.setMarkDownText(it)
    }
}
>>>>>>> dev:app/src/main/java/com/thinlineit/ctrlf/util/BidingAdapter.kt
