package com.thinlineit.ctrlf.page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.thinlineit.ctrlf.notes.NoteDao
import com.thinlineit.ctrlf.notes.TopicDao
<<<<<<< HEAD
import com.thinlineit.ctrlf.repository.network.NoteService
import com.thinlineit.ctrlf.repository.network.PageService
import com.thinlineit.ctrlf.repository.network.TopicService
import com.thinlineit.ctrlf.util.base.BaseViewModel
=======
import com.thinlineit.ctrlf.repository.PageRepository
import kotlinx.coroutines.launch
>>>>>>> dev

class PageViewModel(noteId: Int) : BaseViewModel() {

    private val pageRepository: PageRepository by lazy {
        PageRepository()
    }
    private val noteIdString = MutableLiveData(noteId.toString())
    private val pageInfo = MutableLiveData<PageDao>()
    private val noteDetailInfo = MutableLiveData<NoteDao>()

    private val _openSlidingPane = MutableLiveData<Boolean>()
    val openSlidingPane: LiveData<Boolean>
        get() = _openSlidingPane

    val noteInfo = MutableLiveData<List<TopicDao>>(listOf())
    val topicInfo = MutableLiveData<List<PageDao>>()
    val content = Transformations.map(pageInfo) { it.content }
    val pageTitle = Transformations.map(pageInfo) { it.title }
    val topicTitleTop = MutableLiveData<String>()
    val noteDetailTitle = Transformations.map(noteDetailInfo) { it.title }

    lateinit var topicDetailTitle: String

    init {
        loadNoteInfo()
        loadNoteDetailInfo()
        _openSlidingPane.value = false
    }

    fun openPage(pageId: Int) {
        loadPage(pageId)
    }

    private fun loadPage(pageId: Int) {
<<<<<<< HEAD
        viewModelScope.loadingLaunch {
            try {
                pageInfo.setValue(PageService.retrofitService.getPage(pageId.toString()))
            } catch (e: Exception) {
            }
=======
        viewModelScope.launch {
            pageInfo.setValue(pageRepository.loadPage(pageId))
>>>>>>> dev
        }
    }

    private fun loadNoteInfo() {
<<<<<<< HEAD
        viewModelScope.loadingLaunch {
            try {
                val noteId = noteIdString.value ?: return@loadingLaunch
                noteInfo.setValue(NoteService.retrofitService.getNote(noteId))
            } catch (e: Exception) {
            }
=======
        viewModelScope.launch {
            val noteId = noteIdString.value ?: return@launch
            noteInfo.setValue(pageRepository.loadNoteInfo(noteId))
>>>>>>> dev
        }
    }

    private fun loadNoteDetailInfo() {
<<<<<<< HEAD
        viewModelScope.loadingLaunch {
            try {
                val noteId = noteIdString.value ?: return@loadingLaunch
                noteDetailInfo.setValue(
                    NoteService.retrofitService.getNoteDetail(Integer.parseInt(noteId))
                )
            } catch (e: Exception) {
            }
=======
        viewModelScope.launch {
            val noteId = noteIdString.value ?: return@launch
            noteDetailInfo.setValue(pageRepository.loadNoteDetailInfo(noteId))
>>>>>>> dev
        }
    }

    fun closeSliding() {
        _openSlidingPane.value = false
    }

    fun selectTopic(topicId: Int, topicTitle: String) {
        loadPageList(topicId)
        topicDetailTitle = topicTitle
        topicTitleTop.value = topicTitle
    }

    private fun loadPageList(topicId: Int) {
<<<<<<< HEAD
        viewModelScope.loadingLaunch {
            try {
                topicInfo.setValue(TopicService.retrofitService.getPageList(topicId.toString()))
            } catch (e: Exception) {
            }
=======
        viewModelScope.launch {
            topicInfo.setValue(pageRepository.loadPageList(topicId))
>>>>>>> dev
        }
    }

    fun openSliding() {
        _openSlidingPane.value = true
    }
}
