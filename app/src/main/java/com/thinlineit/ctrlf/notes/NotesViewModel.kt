package com.thinlineit.ctrlf.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thinlineit.ctrlf.network.NoteService
import kotlinx.coroutines.launch
import java.lang.Exception

class NotesViewModel : ViewModel() {
    private val _noteList = MutableLiveData<List<NoteDao>>(listOf())
    val noteList: LiveData<List<NoteDao>>
        get() = _noteList
    val alertLiveData = MutableLiveData<String>()

    init {
        loadNote()
    }

    private fun loadNote() {
        viewModelScope.launch {
            try {
                _noteList.value = NoteService.retrofitService.listNote("")
            } catch (e: Exception) {
                alertLiveData.postValue(e.toString())
            }
        }
    }
}