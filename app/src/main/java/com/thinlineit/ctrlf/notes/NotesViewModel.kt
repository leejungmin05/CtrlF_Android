package com.thinlineit.ctrlf.notes

import androidx.lifecycle.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thinlineit.ctrlf.repository.network.NoteService
import kotlinx.coroutines.launch
import java.lang.Exception

class NotesViewModel : ViewModel() {
    private val _noteList = MutableLiveData<NoteListDao>()
    val noteList: LiveData<NoteListDao>
        get() = _noteList
    val alertLiveData = MutableLiveData<String>()

    val notes = Transformations.map(noteList) { it.notes }

    init {
        loadNote()
    }

    private fun loadNote() {
        viewModelScope.launch {
            try {
                _noteList.value = NoteService.retrofitService.listNote(0)
            } catch (e: Exception) {
                alertLiveData.postValue(e.toString())
            }
        }
    }
}