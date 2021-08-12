package com.thinlineit.ctrlf.page

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.ListItemPageTitleBinding

class PageTitleListAdapter(private val mock: List<PageTitle>, private val clickListener: (Int) -> Unit) :
    RecyclerView.Adapter<PageTitleListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_page_title, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mock.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mock[position],clickListener)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val txtName: TextView = itemView.findViewById(R.id.contentPage)

        fun bind(item:PageTitle,clickListener: (Int) -> Unit){
            txtName.text = item.mock

            val pos = adapterPosition
            if(pos!= RecyclerView.NO_POSITION)
            {
                itemView.setOnClickListener {
                    clickListener(1)
                }
            }
        }
    }
}