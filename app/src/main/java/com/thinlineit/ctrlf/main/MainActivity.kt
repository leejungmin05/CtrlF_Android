package com.thinlineit.ctrlf.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.GridLayoutManager
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val mainViewModel by viewModels<MainViewModel>()
    private val noteAdapter = NoteAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        binding.NoteListRecyclerView.layoutManager = GridLayoutManager(this,3)
        binding.NoteListRecyclerView.adapter = noteAdapter
        binding.mainViewModel = mainViewModel
    }
}