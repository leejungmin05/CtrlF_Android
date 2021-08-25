package com.thinlineit.ctrlf.main

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thinlineit.ctrlf.issue.IssueDao
import com.thinlineit.ctrlf.main.viewpager.FirstFragment
import com.thinlineit.ctrlf.main.viewpager.SecondFragment
import com.thinlineit.ctrlf.repository.network.NoteService
import com.thinlineit.ctrlf.notes.NoteDao
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel : ViewModel() {
    private val _noteList = MutableLiveData<List<NoteDao>>(listOf())
    val noteList: LiveData<List<NoteDao>>
        get() = _noteList

    private val _issueList = MutableLiveData<List<IssueDao>>(emptyList())
    val issueList: LiveData<List<IssueDao>>
        get() = _issueList

    private val _fragmentList = MutableLiveData<List<Fragment>>(emptyList())
    val fragmentList: LiveData<List<Fragment>>
        get() = _fragmentList

    init {
        loadViewPager()
        loadNote()
        loadIssue()
    }

    private fun loadViewPager() {
        _fragmentList.value = createFragment()
    }

    private fun createFragment() : MutableList<Fragment> {
        val fragments : MutableList<Fragment> = arrayListOf()
        fragments.add(FirstFragment())
        fragments.add(SecondFragment())
        return fragments
    }

    private fun loadNote() {
        viewModelScope.launch {
            try {
                _noteList.value = NoteService.retrofitService.listNote("")
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