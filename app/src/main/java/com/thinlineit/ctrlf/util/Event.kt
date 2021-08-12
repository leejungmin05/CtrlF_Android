package com.thinlineit.ctrlf.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

class Event<out T>(private val content: T) {
    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun peekContent(): T = content
}

fun <T> LiveData<Event<T>>.observeIfNotHandled(owner: LifecycleOwner, onChanged: (T) -> Unit) {
    observe(owner) {
        it.getContentIfNotHandled()?.let { value ->
            onChanged(value)
        }
    }
}

enum class Status {
    SUCCESS,
    FAILURE;
}