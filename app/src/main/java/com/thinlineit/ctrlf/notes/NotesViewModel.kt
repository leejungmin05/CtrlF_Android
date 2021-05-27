package com.thinlineit.ctrlf.notes

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thinlineit.ctrlf.network.NoteService
import retrofit2.Call
import retrofit2.Response

class NotesViewModel : ViewModel() {
    val noteList: LiveData<MutableList<NoteDao>>
        get() = _noteList

    private val _noteList = MutableLiveData<MutableList<NoteDao>>(mutableListOf())
    init {
        listNote()
    }
    private fun listNote() {

        NoteService.retrofitService.getAllNote("").enqueue(object :
            retrofit2.Callback<List<NoteDao>>{
            override fun onResponse(
                call: Call<List<NoteDao>>,
                response: Response<List<NoteDao>>
            ) {
                if(response.isSuccessful){
                    var noteArrList = response.body()!!.toMutableList()

                    _noteList.postValue(noteArrList)

                }
                else{
                    Log.d("test",response.code().toString())
                }

            }

            override fun onFailure(call: Call<List<NoteDao>>, t: Throwable) {
                Log.d("test","hhhhhh")
            }
        })


    }
}