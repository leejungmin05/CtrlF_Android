package com.thinlineit.ctrlf.page

import androidx.lifecycle.*
import com.thinlineit.ctrlf.network.NoteService
import com.thinlineit.ctrlf.network.PageService
import com.thinlineit.ctrlf.notes.NoteDao
import kotlinx.coroutines.launch
import java.lang.Exception

class PageViewModel(noteId: Int) : ViewModel() {
    private val _noteIdString = MutableLiveData<String>(noteId.toString())
    val noteIdString: LiveData<String>
        get() = _noteIdString

    private val _noteInfo = MutableLiveData<NoteDao>()
    val noteInfo: LiveData<NoteDao>
        get() = _noteInfo

    private val _pageInfo = MutableLiveData<PageDao>()
    val pageInfo: LiveData<PageDao>
        get() = _pageInfo

    private val _slidingOpen = MutableLiveData<Int>()
    val slidingOpen : LiveData<Int>
        get() = _slidingOpen

    val content = Transformations.map(pageInfo) { it.content }
    val topicList = Transformations.map(noteInfo) { it.topicList }

    init {
        loadPage(1)
        loadNoteInfo()
        _slidingOpen.value = 0
    }

    private fun loadPage(pageId: Int) {
        viewModelScope.launch {
            try {
                _pageInfo.setValue(PageService.retrofitService.getPage(pageId))
            } catch (e: Exception) {
            }
        }
    }

    private fun loadNoteInfo() {
        //TODO: Load the Sub-information of note using "getNote" api
        viewModelScope.launch {
            try {
                _noteInfo.setValue(NoteService.retrofitService.getNote(Integer.parseInt(noteIdString.value.toString())))
            } catch (e: Exception) {
            }
        }
    }

    fun selectTopic(topicId: Int) {
        loadPageList(topicId)
    }

    private fun loadPageList(topicId: Int) {
        //TODO: Load the list of the pagetitle using "?" api

    }
    fun openSliding(){
        _slidingOpen.value = _slidingOpen.value?.plus(1)
    }
}