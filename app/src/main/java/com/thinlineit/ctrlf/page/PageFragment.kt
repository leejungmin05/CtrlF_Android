package com.thinlineit.ctrlf.page

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.FragmentPageBinding
import kotlinx.android.synthetic.main.fragment_page.*

class PageFragment : Fragment() {

    lateinit var topicListAdapter : TopicListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentPageBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_page, container, false
        )
        val noteId = PageFragmentArgs.fromBundle(requireArguments()).noteId
        val viewModelFactory = PageViewModelFactory(noteId)
        val pageViewModel = ViewModelProvider(this, viewModelFactory).get(PageViewModel::class.java)
        binding.pageViewModel = pageViewModel
        binding.lifecycleOwner = this

        setHasOptionsMenu(true)

        topicListAdapter = TopicListAdapter()
        binding.PageListRecyclerView.adapter = topicListAdapter

        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                dl_main_drawer_root.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}