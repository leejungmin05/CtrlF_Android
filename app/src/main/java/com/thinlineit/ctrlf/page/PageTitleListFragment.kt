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
import kotlinx.android.synthetic.main.fragment_topic_title.*

class PageTitleListFragment : Fragment() {
    val mock : List<PageTitle> = listOf(
        PageTitle("one"),
        PageTitle("two"),
        PageTitle("three"),
        PageTitle("four"),
        PageTitle("five")
    )
    private val pageTitleListAdapter = PageTitleListAdapter(mock){ pageId ->
        pageViewModel.openSliding()
    }
    val pageViewModel by activityViewModels<PageViewModel>()
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