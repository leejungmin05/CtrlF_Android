package com.thinlineit.ctrlf.main

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.FragmentMainBinding
import com.thinlineit.ctrlf.main.viewpager.MainViewPagerAdapter
import com.thinlineit.ctrlf.notes.NotesAdapter

class MainFragment : Fragment() {

    private val mainViewModel by viewModels<MainViewModel>()
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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        val binding =
            (DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_main,
                container,
                false
            ) as FragmentMainBinding).apply {
                this.mainViewModel = this@MainFragment.mainViewModel
                lifecycleOwner = this@MainFragment
                (activity as AppCompatActivity).setSupportActionBar(toolBar)
                (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
                mainViewPager.adapter = MainViewPagerAdapter(requireActivity())
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_main,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.userCircleBtn -> {
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToLogoutFragment()
                )
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }
}