package com.thinlineit.ctrlf.issue.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thinlineit.ctrlf.issue.IssueDao

class IssueDetailViewModel(issue: IssueDao) : ViewModel() {
    private val _issueInfo = MutableLiveData<IssueDao>(issue)
    val issueInfo: LiveData<IssueDao>
        get() = _issueInfo
}
