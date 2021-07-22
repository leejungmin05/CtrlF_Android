package com.thinlineit.ctrlf.issue.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thinlineit.ctrlf.issue.IssueDao
import com.thinlineit.ctrlf.network.IssueService
import kotlinx.coroutines.launch
import java.lang.Exception

class IssueListViewModel : ViewModel() {
    private val _issueList = MutableLiveData<List<IssueDao>>(emptyList())
    val issueList: LiveData<List<IssueDao>>
        get() = _issueList

    init {
        loadIssue()
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
