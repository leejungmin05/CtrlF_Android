package com.thinlineit.ctrlf.page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PageViewModel(val noteId:Int) : ViewModel() {
    private val _noteIdString = MutableLiveData<String>(noteId.toString())
    val noteIdString = _noteIdString
}