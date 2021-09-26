package com.thinlineit.ctrlf.util.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()

    fun CoroutineScope.loadingLaunch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ) {
        launch(context, start) {
            isLoading.postValue(true)
            block.invoke(this)
            isLoading.postValue(false)
        }
    }
}
