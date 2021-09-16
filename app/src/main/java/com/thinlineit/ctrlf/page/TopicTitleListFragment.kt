package com.thinlineit.ctrlf.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    private val topicListAdapter = TopicTitleListAdapter { topicId, topicTitle, topicCreatedAt ->
        pageViewModel.selectTopic(topicId, topicTitle, topicCreatedAt)
        this.findNavController().navigate(
            TopicTitleListFragmentDirections.actionNotesFragmentToPageFragment()
        )
    }
    private val itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(topicListAdapter))
    private val pageViewModel by activityViewModels<PageViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding =
            FragmentTopicTitleBinding.inflate(
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
                when (pageViewModel!!.noteColorNum % 3) {
                    1 -> noteBook.setImageResource(R.drawable.ic_note_1)
                    2 -> noteBook.setImageResource(R.drawable.ic_note_2)
                    else -> noteBook.setImageResource(R.drawable.ic_note_3)
                }
            }
        return binding.root
    }
}
