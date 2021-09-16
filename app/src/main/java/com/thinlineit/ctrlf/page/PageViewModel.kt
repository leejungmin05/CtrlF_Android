package com.thinlineit.ctrlf.page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thinlineit.ctrlf.notes.NoteDao
import com.thinlineit.ctrlf.notes.TopicDao
import com.thinlineit.ctrlf.repository.PageRepository
import kotlinx.coroutines.launch

class PageViewModel(noteId: Int) : ViewModel() {
    private val pageRepository: PageRepository by lazy {
        PageRepository()
    }
    val noteIdString = MutableLiveData(noteId.toString())
    val noteInfo = MutableLiveData<List<TopicDao>>(listOf())
    val pageInfo = MutableLiveData<PageDao>()
    val noteDetailInfo = MutableLiveData<NoteDao>()

    private val _slidingOpen = MutableLiveData<Boolean>()

    val slidingOpen: LiveData<Boolean>
        get() = _slidingOpen
    val topicInfo = MutableLiveData<List<PageDao>>()

    val content = Transformations.map(pageInfo) { it.content }

    // TODO : add Topic,Page Num
    val noteDetailTitle = Transformations.map(noteDetailInfo) { it.title }

    // TODO : add Page Num
    lateinit var topicDetailTitle: String
    lateinit var topicDetailCreatedAt: String

    val noteColorNum = noteId

    init {
        loadPage(1)
        loadNoteInfo()
        loadNoteDetailInfo()
        _slidingOpen.value = false
    }

    fun openPage(pageId: Int) {
        loadPage(pageId)
    }

    private fun loadPage(pageId: Int) {
        viewModelScope.launch {
            pageInfo.setValue(pageRepository.loadPage(pageId))
        }
    }

    private fun loadNoteInfo() {
        viewModelScope.launch {
            val noteId = noteIdString.value ?: return@launch
            noteInfo.setValue(pageRepository.loadNoteInfo(noteId))
        }
    }

    private fun loadNoteDetailInfo() {
        viewModelScope.launch {
            val noteId = noteIdString.value ?: return@launch
            noteDetailInfo.setValue(pageRepository.loadNoteDetailInfo(noteId))
        }
    }

    fun closeSliding() {
        _slidingOpen.value = false
    }

    fun selectTopic(topicId: Int, topicTitle: String, topicCreatedAt: String) {
        loadPageList(topicId)
        topicDetailTitle = topicTitle
        topicDetailCreatedAt = topicCreatedAt
    }

    private fun loadPageList(topicId: Int) {
        viewModelScope.launch {
            topicInfo.setValue(pageRepository.loadPageList(topicId))
        }
    }

    fun openSliding() {
        _slidingOpen.value = true
    }
}
