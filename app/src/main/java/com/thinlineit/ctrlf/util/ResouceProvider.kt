package com.thinlineit.ctrlf.util

import android.content.Context

class ResourceProvider(private val context: Context) {
    fun getString(stringResId: Int): String {
        return context.getString(stringResId)
    }
}
