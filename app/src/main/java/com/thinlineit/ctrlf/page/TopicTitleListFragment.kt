package com.thinlineit.ctrlf.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.thinlineit.ctrlf.databinding.FragmentTopicTitleBinding
import kotlinx.android.synthetic.main.activity_page.*

class TopicTitleListFragment : Fragment() {
    private val topicListAdapter = TopicTitleListAdapter { topicId, topicTitle, topicCreatedAt ->
        pageViewModel.selectTopic(topicId, topicTitle, topicCreatedAt)
        // pageViewModel.oneTopic(topicTitle, topicCreatedAt)
        this.findNavController().navigate(
            TopicTitleListFragmentDirections.actionNotesFragmentToPageFragment()
        )
    }
    private val pageViewModel by activityViewModels<PageViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding =
            FragmentTopicTitleBinding.inflate(layoutInflater).apply {
                this.pageViewModel = this@TopicTitleListFragment.pageViewModel
                lifecycleOwner = this@TopicTitleListFragment
                topicListRecyclerView.adapter = topicListAdapter
            }
        return binding.root
    }
}
