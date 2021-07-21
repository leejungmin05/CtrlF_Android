package com.thinlineit.ctrlf.issue.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.ActivityIssueDetailBinding
import com.thinlineit.ctrlf.issue.IssueDao

class IssueDetailActivity : AppCompatActivity() {

    private val binding: ActivityIssueDetailBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_issue_detail)
    }

    companion object {
        lateinit var issue: IssueDao
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        issue = intent.getSerializableExtra("issueInfo") as IssueDao
        val viewModelFactory = IssueDetailViewModelFactory(issue)
        val issueDetailViewModel =
            ViewModelProvider(this, viewModelFactory).get(IssueDetailViewModel::class.java)
        binding.issueDetailViewModel = issueDetailViewModel
        binding.lifecycleOwner = this
    }
}