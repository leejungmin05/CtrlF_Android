package com.thinlineit.ctrlf.util

import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.mukesh.MarkdownView
import com.thinlineit.ctrlf.registration.RegistrationViewmodel


@BindingAdapter("app:data")
fun <T> setRecyclerViewData(recyclerView: RecyclerView, data: T) {
    if (recyclerView.adapter is BindingRecyclerViewAdapter<*> && data != null) {
        (recyclerView.adapter as BindingRecyclerViewAdapter<T>).setData(data)
    }
}

@BindingAdapter("markdownString")
fun setMarkdownString(markdownView: MarkdownView, string: String?) {
    string?.let {
        markdownView.setMarkDownText(it)
    }
}

@BindingAdapter("app:addTextChangeListener")
fun addTextChangeListener(view: EditText, viewModel: ViewModel) {
    view.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            when (viewModel) {
                is RegistrationViewmodel -> viewModel.checkPasswordSame()
            }
        }
    })
}

@BindingAdapter("app:onEditorActionListener")
fun EditText.onEditorAction(callback: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            callback.invoke()
            true
        }
        false
    }
}