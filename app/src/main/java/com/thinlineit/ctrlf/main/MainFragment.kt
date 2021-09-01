package com.thinlineit.ctrlf.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.FragmentMainBinding
import com.thinlineit.ctrlf.main.viewpager.MainViewPagerAdapter
import com.thinlineit.ctrlf.notes.NotesAdapter

class MainFragment : Fragment() {

    private val mainViewModel by viewModels<MainViewModel>()
    private val mainViewPagerAdapter by lazy { MainViewPagerAdapter(requireActivity()) }
    private val noteAdapter = NotesAdapter { noteId ->
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
        val binding = FragmentMainBinding.inflate(inflater).apply {
            mainViewModel = this@MainFragment.mainViewModel
            lifecycleOwner = this@MainFragment
            (requireActivity() as AppCompatActivity).setSupportActionBar(toolBar)
            (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(
                false
            )
            mainViewPager.adapter = mainViewPagerAdapter
            noteListRecyclerView.adapter = noteAdapter
            issueListRecyclerView.adapter = issueAdapter
            showAllNoteTextView.setOnClickListener {
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToNotesFragment()
                )
            }
            showAllIssueTextView.setOnClickListener {
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToIssueListFragment()
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
                MainFragmentDirections.actionMainFragmentToLogoutFragment()
            )
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
