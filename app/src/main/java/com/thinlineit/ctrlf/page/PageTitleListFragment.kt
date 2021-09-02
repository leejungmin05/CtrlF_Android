package com.thinlineit.ctrlf.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.thinlineit.ctrlf.databinding.FragmentPageTitleBinding
import kotlinx.android.synthetic.main.fragment_page_title.*
import kotlinx.android.synthetic.main.fragment_topic_title.*

class PageTitleListFragment : Fragment() {
<<<<<<< HEAD
    private val pageTitleListAdapter = PageTitleListAdapter() { pageId ->
=======
    private val pageTitleListAdapter = PageTitleListAdapter { pageId ->
>>>>>>> dev
        pageViewModel.openSliding()
        pageViewModel.openPage(pageId)
    }
    private val pageViewModel by activityViewModels<PageViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPageTitleBinding.inflate(layoutInflater).apply {
            this.pageViewModel = this@PageTitleListFragment.pageViewModel
            lifecycleOwner = this@PageTitleListFragment
            pageListRecyclerView.adapter = pageTitleListAdapter
        }
        return binding.root
    }
}
