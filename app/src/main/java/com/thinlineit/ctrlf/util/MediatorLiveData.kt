package com.thinlineit.ctrlf.util

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData


fun <T> MediatorLiveData<T>.addSourceList(
    vararg liveDataArgument: MutableLiveData<*>,
    onChange: () -> T
) {
    liveDataArgument.forEach {
        this.addSource(it) { value = onChange() }
    }
}
