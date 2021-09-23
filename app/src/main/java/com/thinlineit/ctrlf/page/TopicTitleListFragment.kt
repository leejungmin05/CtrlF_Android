package com.thinlineit.ctrlf.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.FragmentTopicTitleBinding
import kotlinx.android.synthetic.main.activity_page.*
import kotlinx.android.synthetic.main.fragment_topic_title.*

class TopicTitleListFragment : Fragment() {
    private val topicListAdapter = TopicTitleListAdapter { topicId, topicTitle ->
        pageViewModel.selectTopic(topicId, topicTitle)
        this.findNavController().navigate(
            TopicTitleListFragmentDirections.actionNotesFragmentToPageFragment()
        )
    }
    private val itemTouchHelper = ItemTouchHelper(SwipeController(topicListAdapter))
    private val pageViewModel by activityViewModels<PageViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTopicTitleBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            this.pageViewModel = this@TopicTitleListFragment.pageViewModel
            lifecycleOwner = this@TopicTitleListFragment
            topicListRecyclerView.adapter = topicListAdapter
            itemTouchHelper.attachToRecyclerView(topicListRecyclerView)
            topicListRecyclerView.layoutManager =
                LinearLayoutManager(this@TopicTitleListFragment.context)
            // TODO: 툴바 이미지 변경, 클릭 시 준비중입니다 다이얼로그 적용
            topicListBottomNavigationBar.setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.addTopicPage -> {
                        Toast.makeText(getActivity(), "해당 서비스는 준비중입니다.", Toast.LENGTH_SHORT).show()
                        return@setOnNavigationItemSelectedListener true
                    }
                    else -> return@setOnNavigationItemSelectedListener false
                }
            }
        }
        return binding.root
    }
}
