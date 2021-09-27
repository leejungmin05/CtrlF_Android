package com.thinlineit.ctrlf.util

import android.view.View
import androidx.appcompat.content.res.AppCompatResources

fun View.setBackgroundById(id: Int) {
    background = AppCompatResources.getDrawable(this.context, id)
}
