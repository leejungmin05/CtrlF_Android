package com.thinlineit.ctrlf.main.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.thinlineit.ctrlf.util.BindingFragmentStateAdapter

class MainViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity), BindingFragmentStateAdapter<List<Fragment>> {
    private var fragmentList = emptyList<Fragment>()

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun setData(data: List<Fragment>) {
        fragmentList = data
        notifyDataSetChanged()
    }
}