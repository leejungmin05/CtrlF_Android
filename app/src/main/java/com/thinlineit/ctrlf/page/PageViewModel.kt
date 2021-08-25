package com.thinlineit.ctrlf.page

import androidx.lifecycle.*
import com.thinlineit.ctrlf.repository.network.NoteService
import com.thinlineit.ctrlf.repository.network.PageService
import com.thinlineit.ctrlf.repository.network.TopicService
import com.thinlineit.ctrlf.notes.NoteDao
import com.thinlineit.ctrlf.notes.TopicDao
import kotlinx.coroutines.launch
import java.lang.Exception

class PageViewModel(noteId: Int) : ViewModel() {

    val noteIdString = MutableLiveData<String>(noteId.toString())
    val noteInfo = MutableLiveData<List<TopicDao>>(listOf())
    val pageInfo = MutableLiveData<PageDao>()
    val noteDetailInfo = MutableLiveData<NoteDao>()

    private val _slidingOpen = MutableLiveData<Boolean>()

    val slidingOpen: LiveData<Boolean>
        get() = _slidingOpen
    val topicInfo = MutableLiveData<List<PageDao>>()

    val content = Transformations.map(pageInfo) { it.content }

    //TODO : add noteDatail created_at and Topic,Page Num
    val noteDetailTitle = Transformations.map(noteDetailInfo) { it.title }

    //TODO : add Page Num
    lateinit var topicDetailTitle: String
    lateinit var topicDetailCreatedAt: String

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
            try {
                pageInfo.setValue(PageService.retrofitService.getPage(pageId.toString()))
            } catch (e: Exception) {
            }
        }
    }

    private fun loadNoteInfo() {
        viewModelScope.launch {
            try {
                val noteId = noteIdString.value?:return@launch
                noteInfo.setValue(NoteService.retrofitService.getNote(noteId))
            } catch (e: Exception) {
            }
        }
    }

    private fun loadNoteDetailInfo() {
        viewModelScope.launch {
            try {
                val noteId = noteIdString.value?:return@launch
                noteDetailInfo.setValue(NoteService.retrofitService.getNoteDetail(Integer.parseInt(noteId)))
            } catch (e: Exception) {
            }
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
            try {
                topicInfo.setValue(TopicService.retrofitService.getPageList(topicId.toString()))
            } catch (e: Exception) {
            }
        }
    }

    fun openSliding() {
        _slidingOpen.value = true
    }
}