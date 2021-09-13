package com.thinlineit.ctrlf.page

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PageEditorAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    private var fragments: ArrayList<Fragment> = ArrayList()
    lateinit var viewModel: PageEditorViewModel

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

    fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
        notifyItemInserted(fragments.size - 1)
    }
}