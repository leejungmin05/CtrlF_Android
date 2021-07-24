package com.thinlineit.ctrlf.page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.FragmentPageBinding

class PageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val noteId = PageFragmentArgs.fromBundle(requireArguments()).noteId
        val viewModelFactory = PageViewModelFactory(noteId)
        val pageViewModel =
            ViewModelProvider(this, viewModelFactory).get(PageViewModel::class.java)
        val binding = (DataBindingUtil.inflate(
            inflater, R.layout.fragment_page, container, false
        ) as FragmentPageBinding).apply {
            this.pageViewModel = pageViewModel
            lifecycleOwner = this@PageFragment
        }
        return binding.root
    }
}