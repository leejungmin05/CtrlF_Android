package com.thinlineit.ctrlf.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.thinlineit.ctrlf.util.BindingRecyclerViewAdapter
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.ListItemNoteBinding

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.ViewHolder>(),
    BindingRecyclerViewAdapter<List<NoteDao>> {
    var noteList = listOf<NoteDao>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent)

    override fun getItemCount(): Int = noteList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val noteDao = noteList[position]
        holder.bind(noteDao)
    }

    class ViewHolder(private val dataBinding: ListItemNoteBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {
        fun bind(noteDao: NoteDao) {
            dataBinding.note = noteDao
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val dataBinding = DataBindingUtil.inflate<ListItemNoteBinding>(
                    layoutInflater,
                    R.layout.list_item_note,
                    parent,
                    false
                )
                return ViewHolder(dataBinding)
            }
        }
    }

    override fun setData(data: List<NoteDao>) {
        noteList = data
        notifyDataSetChanged()
    }
}
