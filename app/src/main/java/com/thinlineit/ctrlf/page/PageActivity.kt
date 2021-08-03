package com.thinlineit.ctrlf.page

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.ActivityPageBinding
import kotlinx.android.synthetic.main.activity_page.*

class PageActivity : AppCompatActivity() {
    private val binding: ActivityPageBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_page
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page)
        val noteId = intent.getIntExtra(NOTE_ID, 0)
        val viewModelFactory = PageViewModelFactory(noteId)
        val pageViewModel = ViewModelProvider(this, viewModelFactory).get(PageViewModel::class.java)
        binding.apply {
            this.pageViewModel = pageViewModel
            lifecycleOwner = this@PageActivity
        }
        pageListRecyclerView.adapter = TopicListAdapter()
    }

    companion object {
        const val NOTE_ID = "noteId"
    }
}