package com.thinlineit.ctrlf.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.FragmentMainBinding
import com.thinlineit.ctrlf.main.viewpager.MainViewPagerAdapter
import com.thinlineit.ctrlf.notes.NotesAdapter

class MainFragment : Fragment() {

    private val mainViewModel by viewModels<MainViewModel>()
    private val noteAdapter = NotesAdapter(NotesAdapter.TYPE_HORIZONTAL) { noteId ->
        this.findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToPageActivity(noteId)
        )
    }
    private val issueAdapter = MainIssueAdapter { issueInfo ->
        this.findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToIssueDetailActivity(issueInfo)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        val binding = FragmentMainBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            mainViewModel = this@MainFragment.mainViewModel
            lifecycleOwner = this@MainFragment
            (requireActivity() as AppCompatActivity).setSupportActionBar(toolBar)
            (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(
                false
            )
            mainViewPager.adapter = MainViewPagerAdapter(requireActivity())
            noteListRecyclerView.adapter = noteAdapter
            issueListRecyclerView.adapter = issueAdapter
            showAllNoteTextView.setOnClickListener {
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToNotesFragment()
                )
            }
            showAllIssueTextView.setOnClickListener {
                Toast.makeText(activity, R.string.notice_prepare, Toast.LENGTH_LONG).show()
            }
            this@MainFragment.mainViewModel.issueList.observe(viewLifecycleOwner) {
                updateIssueViewVisibility(
                    issueListRecyclerView,
                    issueEmptyText,
                    it.isEmpty()
                )
            }
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) =
        inflater.inflate(R.menu.toolbar_main, menu)

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.userCircleBtn -> {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToLogoutActivity()
            )
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun updateIssueViewVisibility(
        recyclerView: RecyclerView,
        textView: TextView,
        isEmpty: Boolean
    ) {
        if (isEmpty) {
            recyclerView.visibility = View.GONE
            textView.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.VISIBLE
            textView.visibility = View.GONE
        }
    }
}
