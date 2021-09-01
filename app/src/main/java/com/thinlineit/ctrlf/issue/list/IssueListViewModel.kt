package com.thinlineit.ctrlf.issue.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thinlineit.ctrlf.issue.IssueDao

class IssueListViewModel : ViewModel() {
    private val _issueList = MutableLiveData<List<IssueDao>>(emptyList())
    val issueList: LiveData<List<IssueDao>>
        get() = _issueList

    init {
        loadIssue()
    }

    private fun loadIssue() {
        // TODO: Load the list of issue using "getIssue" api
        _issueList.value = createIssue()
    }

    private fun createIssue(): List<IssueDao> {
        val contentStr =
            "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz" +
                "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"
        return (1..9).map { i ->
            if (i % 2 != 0) IssueDao(i, "title$i", 1, 1, "2021-07-12", contentStr)
            else IssueDao(i, "title$i", 1, 1, "2021-07-12", contentStr + contentStr)
        }
    }
}
