package com.thinlineit.ctrlf.page

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.TopicListPageBinding
import com.thinlineit.ctrlf.notes.TopicDao
import com.thinlineit.ctrlf.util.BindingRecyclerViewAdapter

class TopicListAdapter() :
    RecyclerView.Adapter<TopicListAdapter.ViewHolder>(), BindingRecyclerViewAdapter<List<TopicDao>> {
    var topicList = emptyList<TopicDao>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent)

    override fun getItemCount(): Int = topicList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val topicDao = topicList[position]
        holder.bind(topicDao)
    }

    class ViewHolder(private val dataBinding: TopicListPageBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {
        fun bind(topicDao: TopicDao) {
            dataBinding.topic = topicDao
        }

        companion object {
            fun from(parent: ViewGroup): TopicListAdapter.ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val dataBinding = DataBindingUtil.inflate<TopicListPageBinding>(
                    layoutInflater,
                    R.layout.topic_list_page,
                    parent,
                    false
                )
                return ViewHolder(dataBinding)
            }
        }
    }
    override fun setData(data: List<TopicDao>) {
        topicList = data
        notifyDataSetChanged()
    }
}