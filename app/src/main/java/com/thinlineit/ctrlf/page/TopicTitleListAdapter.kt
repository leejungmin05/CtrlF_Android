package com.thinlineit.ctrlf.page

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.ListItemTopicTitleBinding
import com.thinlineit.ctrlf.notes.TopicDao
import com.thinlineit.ctrlf.util.BindingRecyclerViewAdapter

class TopicTitleListAdapter(private val clickListener: (Int, String) -> Unit) :
    RecyclerView.Adapter<TopicTitleListAdapter.ViewHolder>(),
    BindingRecyclerViewAdapter<List<TopicDao>>,
    ItemTouchHelperListener {
    var topicList = emptyList<TopicDao>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent)

    override fun getItemCount(): Int = topicList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val topicDao = topicList[position]
        holder.bind(topicDao, clickListener)
    }

    // TODO: 준비중입니다 토스트 메세지 -> 다이얼로그
    override fun onDelete(context: Context) {
        TopicFragmentDialog(context).topicDialog(context)
    }

    // TODO: 준비중입니다 토스트 메세지 -> 다이얼로그
    override fun onModify(context: Context) {
        TopicFragmentDialog(context).topicDialog(context)
    }

    class ViewHolder(private val dataBinding: ListItemTopicTitleBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {
        fun bind(topicDao: TopicDao, clickListener: (Int, String) -> Unit) {
            dataBinding.topic = topicDao
            dataBinding.root.setOnClickListener {
                clickListener(topicDao.id, topicDao.title)
            }
        }

        companion object {
            fun from(parent: ViewGroup): TopicTitleListAdapter.ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val dataBinding = DataBindingUtil.inflate<ListItemTopicTitleBinding>(
                    layoutInflater,
                    R.layout.list_item_topic_title,
                    parent,
                    false
                )
                return ViewHolder(dataBinding)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setData(data: List<TopicDao>) {
        topicList = data
        notifyDataSetChanged()
    }
}
