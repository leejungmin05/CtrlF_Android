package com.thinlineit.ctrlf.issue.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.ListItemIssueBinding
import com.thinlineit.ctrlf.issue.IssueDao
import com.thinlineit.ctrlf.util.BindingRecyclerViewAdapter

class IssueListAdapter(private val clickListener: (IssueDao) -> Unit) :
    RecyclerView.Adapter<IssueListAdapter.ViewHolder>(),
    BindingRecyclerViewAdapter<List<IssueDao>> {
    var issueList = emptyList<IssueDao>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent)

    override fun getItemCount(): Int = issueList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val issueDao = issueList[position]
        holder.bind(issueDao, clickListener)
    }

    class ViewHolder(private val dataBinding: ListItemIssueBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {
        fun bind(issueDao: IssueDao, clickListener: (IssueDao) -> Unit) {
            dataBinding.issue = issueDao
            dataBinding.root.setOnClickListener {
                clickListener(issueDao)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val dataBinding = DataBindingUtil.inflate<ListItemIssueBinding>(
                    layoutInflater,
                    R.layout.list_item_issue,
                    parent,
                    false
                )
                return ViewHolder(dataBinding)
            }
        }
    }

    override fun setData(data: List<IssueDao>) {
        issueList = data
        notifyDataSetChanged()
    }
}
