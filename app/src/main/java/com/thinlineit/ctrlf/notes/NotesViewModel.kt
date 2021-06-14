package com.thinlineit.ctrlf.notes

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thinlineit.ctrlf.network.NoteService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception

class NotesViewModel : ViewModel() {
    val noteList: LiveData<List<NoteDao>>
        get() = _noteList
    val alertLiveData = MutableLiveData<String>()

    private val _noteList = MutableLiveData<List<NoteDao>>(listOf())

    init {
            loadNote()
    }

    private fun loadNote() {
        viewModelScope.launch {
            try {
                _noteList.setValue(NoteService.retrofitService.listNote(""))
            } catch(e : Exception) {
                alertLiveData.postValue(e.toString())
            }
        }
    }
}