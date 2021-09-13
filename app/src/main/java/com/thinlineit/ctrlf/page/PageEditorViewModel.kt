package com.thinlineit.ctrlf.page

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PageEditorViewModel(private val pageInfo: PageDao) : ViewModel() {
    private val pageEditorRepository = PageEditorRepository()
    val content = MutableLiveData<String>(pageInfo.content)

    fun complete() {
        // TODO: if it's true, using update pageAPI
        pageEditorRepository.complete(pageInfo)
    }
}
