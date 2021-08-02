package com.thinlineit.ctrlf.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.FragmentMainBinding
import com.thinlineit.ctrlf.notes.NotesAdapter

class MainFragment : Fragment() {

    private val mainViewModel by viewModels<MainViewModel>()
    private val noteAdapter = NotesAdapter { noteId ->
        this.findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToPageFragment(noteId)
        )
    }
    private val issueAdapter = MainIssueAdapter { issueInfo ->
        this.findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToIssueDetailActivity(issueInfo)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            (DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_main,
                container,
                false
            ) as FragmentMainBinding).apply {
                this.mainViewModel = this@MainFragment.mainViewModel
                lifecycleOwner = this@MainFragment
                noteListRecyclerView.adapter = noteAdapter
                issueListRecyclerView.adapter = issueAdapter
                textNoteAllView.setOnClickListener {
                    findNavController().navigate(
                        MainFragmentDirections.actionMainFragmentToNotesFragment()
                    )
                }

                textIssueAllView.setOnClickListener {
                    findNavController().navigate(
                        MainFragmentDirections.actionMainFragmentToIssueListFragment()
                    )
                }
            }
        return binding.root
    }
}