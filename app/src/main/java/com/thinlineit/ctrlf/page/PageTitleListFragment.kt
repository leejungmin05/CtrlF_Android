package com.thinlineit.ctrlf.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.FragmentPageTitleBinding
import com.thinlineit.ctrlf.databinding.FragmentTopicTitleBinding
import kotlinx.android.synthetic.main.fragment_page_title.*
import kotlinx.android.synthetic.main.fragment_topic_title.*

class PageTitleListFragment : Fragment() {
    private val pageTitleListAdapter = PageTitleListAdapter(){ pageId ->
        pageViewModel.openSliding()
        pageViewModel.openPage(pageId)
    }
    private val pageViewModel by activityViewModels<PageViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = (DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_page_title,
            container,
            false
        ) as FragmentPageTitleBinding).apply {
            this.pageViewModel = this@PageTitleListFragment.pageViewModel
            lifecycleOwner = this@PageTitleListFragment
            pageListRecyclerView.adapter = pageTitleListAdapter
        }
        return binding.root
    }
}