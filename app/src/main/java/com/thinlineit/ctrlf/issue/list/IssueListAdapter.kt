package com.thinlineit.ctrlf.issue.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.ListItemIssueBinding
import com.thinlineit.ctrlf.issue.IssueDao
import com.thinlineit.ctrlf.util.BindingRecyclerViewAdapter
import com.thinlineit.ctrlf.util.setBackground

class IssueListAdapter(private val clickListener: (IssueDao) -> Unit) :
    RecyclerView.Adapter<IssueListAdapter.ViewHolder>(),
    BindingRecyclerViewAdapter<List<IssueDao>> {
    private var issueList = emptyList<IssueDao>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent)

    override fun getItemCount(): Int = issueList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val issueDao = issueList[position]
        holder.bind(issueDao, clickListener,position)
    }

    class ViewHolder(private val dataBinding: ListItemIssueBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {
        fun bind(issueDao: IssueDao, clickListener: (IssueDao) -> Unit, position: Int) {
            var resourceId: Int
            when (position % 3) {
                1 -> resourceId = R.drawable.ic_issue_2
                2 -> resourceId = R.drawable.ic_issue_3
                else -> resourceId = R.drawable.ic_issue_1
            }
            dataBinding.apply {
                issueItem.setBackground(resourceId)
                issue = issueDao
                root.setOnClickListener {
                    clickListener(issueDao)
                }
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
