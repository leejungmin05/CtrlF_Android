package com.thinlineit.ctrlf.main

import android.util.Log
import androidx.lifecycle.*
import com.thinlineit.ctrlf.issue.IssueDao
import com.thinlineit.ctrlf.network.NoteService
import com.thinlineit.ctrlf.notes.AllNoteDao
import com.thinlineit.ctrlf.notes.NoteDao
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel : ViewModel() {
    private val _noteList = MutableLiveData<AllNoteDao>()
    val noteList: LiveData<AllNoteDao>
        get() = _noteList

    private val _issueList = MutableLiveData<List<IssueDao>>(emptyList())
    val issueList: LiveData<List<IssueDao>>
        get() = _issueList

    val notes = Transformations.map(noteList) { it.notes}

    init {
        loadNote()
        loadIssue()
    }

    private fun loadNote() {
        viewModelScope.launch {
            try {
                _noteList.value = NoteService.retrofitService.listNote(0)
            } catch (e: Exception) {
            }
        }
    }

    private fun loadIssue() {
        //TODO: Load the list of issue using "getIssue" api
        _issueList.value = createIssue()
    }

    private fun createIssue(): MutableList<IssueDao> {
        val imsiList: MutableList<IssueDao> = arrayListOf()
        var contentStr =
            "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"
        for (i in 1..9) {
            if (i % 2 != 0) {
                imsiList.add(IssueDao(i, "title${i}", 1, 1, "2021-07-12", contentStr))
            } else {
                imsiList.add(IssueDao(i, "title${i}", 1, 1, "2021-07-12", contentStr + contentStr))
            }
        }
        return imsiList
    }
}