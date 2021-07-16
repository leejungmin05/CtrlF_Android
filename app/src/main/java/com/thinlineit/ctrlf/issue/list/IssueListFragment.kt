package com.thinlineit.ctrlf.issue.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.FragmentIssueListBinding

class IssueListFragment : Fragment() {
    private val issueViewModel by viewModels<IssueListViewModel>()
    private val issueAdapter = IssueListAdapter { issueInfo ->
        this.findNavController().navigate(
            IssueListFragmentDirections.actionIssueListFragmentToIssueDetailActivity(issueInfo)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentIssueListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_issue_list, container, false)
        binding.issueViewModel = issueViewModel
        binding.lifecycleOwner = this
        binding.IssueListRecyclerView.adapter = issueAdapter
        binding.IssueListRecyclerView.layoutManager =
            StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)

        return binding.root
    }
}