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

    val content = Transformations.map(pageInfo) { it.content }

    init {
        loadPage(1)
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
}