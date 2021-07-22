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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val issue = intent.getSerializableExtra(ISSUE_INFO) as IssueDao
        val viewModelFactory = IssueDetailViewModelFactory(issue)
        val issuesDetailViewModel =
            ViewModelProvider(this, viewModelFactory).get(IssueDetailViewModel::class.java)
        binding.apply {
            issueDetailViewModel = issuesDetailViewModel
            lifecycleOwner = this@IssueDetailActivity
        }
    }

    companion object {
        const val ISSUE_INFO = "issueInfo"
    }
}