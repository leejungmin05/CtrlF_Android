package com.thinlineit.ctrlf.page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PageEditorViewModelFactory(private val pageInfo: PageDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PageEditorViewModel::class.java)) {
            return PageEditorViewModel(pageInfo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}