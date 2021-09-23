package com.thinlineit.ctrlf.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.FragmentPageTitleBinding
import kotlinx.android.synthetic.main.fragment_page_title.*
import kotlinx.android.synthetic.main.fragment_topic_title.*

class PageTitleListFragment : Fragment() {
    private val pageTitleListAdapter = PageTitleListAdapter { pageId ->
        pageViewModel.openSliding()
        pageViewModel.openPage(pageId)
    }
    private val pageViewModel by activityViewModels<PageViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentPageTitleBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            this.pageViewModel = this@PageTitleListFragment.pageViewModel
            lifecycleOwner = this@PageTitleListFragment
            pageListRecyclerView.adapter = pageTitleListAdapter
            // TODO: 툴바 이미지 변경, 클릭 시 준비중입니다 다이얼로그 적용
            pageListBottomNavigationBar.setOnNavigationItemSelectedListener { item ->
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
