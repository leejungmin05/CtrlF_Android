package com.thinlineit.ctrlf.page

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.ListItemPageTitleBinding
import com.thinlineit.ctrlf.util.BindingRecyclerViewAdapter

class PageTitleListAdapter(private val clickListener: (Int) -> Unit) :
    RecyclerView.Adapter<PageTitleListAdapter.ViewHolder>(),
    BindingRecyclerViewAdapter<List<PageDao>>,
    ItemTouchHelperListener {

    private var pageList = emptyList<PageDao>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent)

    override fun getItemCount(): Int = pageList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pageDao = pageList[position]
        holder.bind(pageDao, clickListener)
    }
    // TODO: 준비중입니다 토스트 메세지 -> 다이얼로그
    override fun onDelete(context: Context) {
        TopicFragmentDialog(context).topicDialog(context)
    }

    // TODO: 준비중입니다 토스트 메세지 -> 다이얼로그
    override fun onModify(context: Context) {
        TopicFragmentDialog(context).topicDialog(context)
    }

    class ViewHolder(private val dataBinding: ListItemPageTitleBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {
        fun bind(pageDao: PageDao, clickListener: (Int) -> Unit) {
            dataBinding.page = pageDao
            dataBinding.root.setOnClickListener {
                clickListener(pageDao.id ?: return@setOnClickListener)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val dataBinding = DataBindingUtil.inflate<ListItemPageTitleBinding>(
                    layoutInflater,
                    R.layout.list_item_page_title,
                    parent,
                    false
                )
                return ViewHolder(dataBinding)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setData(data: List<PageDao>) {
        pageList = data
        notifyDataSetChanged()
    }
}
