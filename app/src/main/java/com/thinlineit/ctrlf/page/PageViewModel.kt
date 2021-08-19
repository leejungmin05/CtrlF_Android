package com.thinlineit.ctrlf.page

import android.util.Log
import androidx.lifecycle.*
import com.thinlineit.ctrlf.network.NoteService
import com.thinlineit.ctrlf.network.PageService
import com.thinlineit.ctrlf.network.TopicService
import com.thinlineit.ctrlf.notes.NoteDao
import com.thinlineit.ctrlf.notes.TopicDao
import kotlinx.coroutines.launch
import java.lang.Exception

class PageViewModel(noteId: Int) : ViewModel() {
    private val _noteIdString = MutableLiveData<String>(noteId.toString())
    val noteIdString: LiveData<String>
        get() = _noteIdString

    private val _noteInfo = MutableLiveData<List<TopicDao>>(listOf())
    val noteInfo: LiveData<List<TopicDao>>
        get() = _noteInfo

    private val _pageInfo = MutableLiveData<PageDao>()
    val pageInfo: LiveData<PageDao>
        get() = _pageInfo

    private val _slidingOpen = MutableLiveData<Int>()
    val slidingOpen: LiveData<Int>
        get() = _slidingOpen

    private val _topicInfo = MutableLiveData<List<PageDao>>()
    val topicInfo: LiveData<List<PageDao>>
        get() = _topicInfo

    val content = Transformations.map(pageInfo) { it.content }

    init {
        loadPage(1)
        loadNoteInfo()
        _slidingOpen.value = 0
    }

    fun openPage(pageId: Int) {
        loadPage(pageId)
    }

    private fun loadPage(pageId: Int) {
        viewModelScope.launch {
            try {
                _pageInfo.setValue(PageService.retrofitService.getPage(pageId.toString()))
            } catch (e: Exception) {
            }
        }
    }

    private fun loadNoteInfo() {
        //TODO: Load the Sub-information of note using "getNote" api
        viewModelScope.launch {
            try {
                _noteInfo.setValue(NoteService.retrofitService.getNote(noteIdString.value.toString()))
            } catch (e: Exception) {
            }
        }
    }

    fun closeSliding() {
        _slidingOpen.value = _slidingOpen.value?.plus(-1)
    }

    fun selectTopic(topicId: Int) {
        loadPageList(topicId)
    }

    private fun loadPageList(topicId: Int) {
        //TODO: Load the list of the pagetitle using "?" api
        viewModelScope.launch {
            try {
                _topicInfo.setValue(TopicService.retrofitService.getPageList(topicId.toString()))
            } catch (e: Exception) {
            }
        }

    }

    fun openSliding() {
        _slidingOpen.value = _slidingOpen.value?.plus(1)
    }
}