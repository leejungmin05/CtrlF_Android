package com.thinlineit.ctrlf.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotesViewModel : ViewModel() {
    val noteList: LiveData<MutableList<NoteDao>>
        get() = _noteList

    private val _noteList = MutableLiveData<MutableList<NoteDao>>()
    init {
        listNote()
    }
    private fun listNote() {
        _noteList.value = mutableListOf(
            NoteDao(0, "test0"),
            NoteDao(1, "test1"),
            NoteDao(2, "test2"),
            NoteDao(3, "test3"),
            NoteDao(4, "test4")
        )
    }
}