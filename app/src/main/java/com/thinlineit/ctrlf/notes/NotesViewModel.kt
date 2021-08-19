package com.thinlineit.ctrlf.notes

import androidx.lifecycle.*
import com.thinlineit.ctrlf.network.NoteService
import kotlinx.coroutines.launch
import java.lang.Exception

class NotesViewModel : ViewModel() {
    private val _noteList = MutableLiveData<AllNoteDao>()
    val noteList: LiveData<AllNoteDao>
        get() = _noteList
    val alertLiveData = MutableLiveData<String>()

    val notes = Transformations.map(noteList) { it.notes}

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