package com.thinlineit.ctrlf.main

<<<<<<< HEAD
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thinlineit.ctrlf.issue.IssueDao
import com.thinlineit.ctrlf.main.viewpager.FirstFragment
import com.thinlineit.ctrlf.main.viewpager.SecondFragment
import com.thinlineit.ctrlf.notes.NoteDao
=======
import androidx.lifecycle.*
import com.thinlineit.ctrlf.issue.IssueDao
import com.thinlineit.ctrlf.notes.NoteListDao
>>>>>>> dev
import com.thinlineit.ctrlf.repository.network.NoteService
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _noteList = MutableLiveData<NoteListDao>()
    val noteList: LiveData<NoteListDao>
        get() = _noteList

    private val _issueList = MutableLiveData<List<IssueDao>>(emptyList())
    val issueList: LiveData<List<IssueDao>>
        get() = _issueList

<<<<<<< HEAD
    private val _fragmentList = MutableLiveData<List<Fragment>>(emptyList())
    val fragmentList: LiveData<List<Fragment>>
        get() = _fragmentList
=======
    val notes = Transformations.map(noteList) { it.notes }
>>>>>>> dev

    init {
        loadBannerList()
        loadNote()
        loadIssue()
    }

    private fun loadBannerList() {
        _fragmentList.value = initBannerList()
    }

    private fun initBannerList(): MutableList<Fragment> {
        val fragments: MutableList<Fragment> = arrayListOf()

        // SecondFragment는 mock data
        fragments.add(FirstFragment())
        fragments.add(SecondFragment())
        return fragments
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
        // TODO: Load the list of issue using "getIssue" api
        _issueList.value = createIssue()
    }

    private fun createIssue(): MutableList<IssueDao> {
        val imsiList: MutableList<IssueDao> = arrayListOf()
        var contentStr =
            "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"
        for (i in 1..9) {
            if (i % 2 != 0)
                imsiList.add(IssueDao(i, "title" + i, 1, 1, "2021-07-12", contentStr))
            else
                imsiList.add(IssueDao(i, "title" + i, 1, 1, "2021-07-12", contentStr + contentStr))
        }
        return imsiList
    }
}
