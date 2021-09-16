package com.thinlineit.ctrlf.page

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.ListItemTopicTitleBinding
import com.thinlineit.ctrlf.notes.TopicDao
import com.thinlineit.ctrlf.util.BindingRecyclerViewAdapter

class TopicTitleListAdapter(private val clickListener: (Int, String, String) -> Unit) :
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

    // Todo 만약 지워지거나 제목이 바뀌웠을때
    override fun onItemSwipe(position: Int) {
    }

    // Todo 해당 타이틀 삭제 -> 준비중입니다 다이얼로그로 수정
    override fun onDeleteClick(position: Int, viewHolder: RecyclerView.ViewHolder?) {
        val builderDelete = TopicFragmentDeleteDialog(PageActivity.instance)
        builderDelete.topicDialog()
    }

    // Todo 해당 타이틀 수정하기 -> 준비중입니다 다이얼로그로 수정
    override fun onCorrectionClick(position: Int, viewHolder: RecyclerView.ViewHolder?) {
        val builderCorrection = TopicFragmentDialog(PageActivity.instance)
        builderCorrection.topicDialog()
    }

    class ViewHolder(private val dataBinding: ListItemTopicTitleBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {
        fun bind(topicDao: TopicDao, clickListener: (Int, String, String) -> Unit) {
            dataBinding.topic = topicDao
            dataBinding.root.setOnClickListener {
                clickListener(topicDao.id, topicDao.title, topicDao.createdAt)
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
