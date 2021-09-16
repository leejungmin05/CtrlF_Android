package com.thinlineit.ctrlf.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
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
        setHasOptionsMenu(true)
        val binding = FragmentPageTitleBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            this.pageViewModel = this@PageTitleListFragment.pageViewModel
            lifecycleOwner = this@PageTitleListFragment
            // TODO: 툴바 이미지 변경, 클릭 시 준비중입니다 다이얼로그 적용
            (requireActivity() as AppCompatActivity).setSupportActionBar(pageListToolBar)
            (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(
                false
            )
            pageListRecyclerView.adapter = pageTitleListAdapter
        }
        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) =
        inflater.inflate(R.menu.toolber_page, menu)
}
