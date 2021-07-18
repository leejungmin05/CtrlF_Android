package com.thinlineit.ctrlf.page

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.FragmentNotesBinding
import com.thinlineit.ctrlf.databinding.ListItemNoteBinding
import com.thinlineit.ctrlf.databinding.MainLvItemBinding
import com.thinlineit.ctrlf.notes.NoteDao
import com.thinlineit.ctrlf.notes.NotesAdapter
import kotlinx.android.synthetic.main.main_lv_item.view.*

class ListAdapter(list: ArrayList<PageDao>) : RecyclerView.Adapter<ListAdapter.ViewHolder>(){
    //var mList: ArrayList<ContentList> = list 
    var mList = ArrayList<PageDao>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent)

    override fun getItemCount(): Int = mList.size
    /*
    {
        return mList.size
    }*/

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val p = mList.get(position)
        holder.bind(p)
    }

    class ViewHolder(private val dataBinding: MainLvItemBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {
        fun bind(pageDao: PageDao) {
            dataBinding.page = pageDao
        }
        companion object {
            fun from(parent: ViewGroup): ListAdapter.ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val dataBinding = DataBindingUtil.inflate<MainLvItemBinding>(
                    layoutInflater,
                    R.layout.main_lv_item,
                    parent,
                    false
                )
                return ViewHolder(dataBinding)
            }
        }
    }
    /*
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(contents :ContentList) {
            itemView.content.text = contents.content
        }
    }

     */
}