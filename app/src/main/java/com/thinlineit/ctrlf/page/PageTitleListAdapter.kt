package com.thinlineit.ctrlf.page

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.ListItemPageTitleBinding
import com.thinlineit.ctrlf.databinding.ListItemTopicTitleBinding
import com.thinlineit.ctrlf.notes.TopicDao
import com.thinlineit.ctrlf.util.BindingRecyclerViewAdapter

class PageTitleListAdapter(private val clickListener: (Int) -> Unit) :
    RecyclerView.Adapter<PageTitleListAdapter.ViewHolder>(),
    BindingRecyclerViewAdapter<List<PageDao>> {
    private var pageList = emptyList<PageDao>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent)

    override fun getItemCount(): Int = pageList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pageDao = pageList[position]
        holder.bind(pageDao, clickListener)
    }

    class ViewHolder(private val dataBinding: ListItemPageTitleBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {
        fun bind(pageDao: PageDao, clickListener: (Int) -> Unit) {
            dataBinding.page = pageDao
            dataBinding.root.setOnClickListener {
                clickListener(pageDao.id)
            }
        }
        companion object {
            fun from(parent: ViewGroup): PageTitleListAdapter.ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val dataBinding = DataBindingUtil.inflate<ListItemPageTitleBinding>(
                    layoutInflater,
                    R.layout.list_item_page_title,
                    parent,
                    false
                )
                return PageTitleListAdapter.ViewHolder(dataBinding)
            }
        }
    }
    override fun setData(data: List<PageDao>) {
        pageList = data
        notifyDataSetChanged()
    }
}