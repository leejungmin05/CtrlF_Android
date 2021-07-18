package com.thinlineit.ctrlf.page

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.FragmentNotesBinding
import com.thinlineit.ctrlf.databinding.ListItemNoteBinding
import com.thinlineit.ctrlf.notes.NoteDao
import com.thinlineit.ctrlf.notes.NotesAdapter
import kotlinx.android.synthetic.main.main_lv_item.view.*

class ListAdapter(list: ArrayList<ContentList>) : RecyclerView.Adapter<ListAdapter.ViewHolder>(){
    //var mList: ArrayList<ContentList> = list 
    var mList = ArrayList<ContentList>()
    var noteList = listOf<NoteDao>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
        //= ViewHolder.from(parent)
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_lv_item,parent,false)
        //val dataBinding = DataBindingUtil.inflate<FragmentNotesBinding>(view,R.layout.main_lv_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mList.size
    /*
    {
        return mList.size
    }*/

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val p = mList.get(position)
        holder.bind(p)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(contents :ContentList) {
            itemView.content.text = contents.content
        }
    }
}