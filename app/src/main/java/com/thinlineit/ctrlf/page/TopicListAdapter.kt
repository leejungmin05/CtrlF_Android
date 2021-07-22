package com.thinlineit.ctrlf.page

import android.util.Log
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
    var topicList = listOf<TopicDao>()

    interface OnItemClickListener{
        fun onItemClick(v:View, data: TopicDao, pos : Int)
    }
    private var listener : OnItemClickListener? = null

    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent)

    override fun getItemCount(): Int = topicList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val p = topicList.get(position)
        holder.bind(p)
    }

    class ViewHolder(private val dataBinding: TopicListPageBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {
        fun bind(topicDao: TopicDao) {
            dataBinding.page = topicDao

            val pos = adapterPosition
            val tag = "ActivityLife"
            if(pos!= RecyclerView.NO_POSITION)
            {
                itemView.setOnClickListener {
                    Log.d(tag,"checklist()")

                }
            }
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
        //TODO("Not yet implemented")
        topicList = data
        notifyDataSetChanged()
    }
}