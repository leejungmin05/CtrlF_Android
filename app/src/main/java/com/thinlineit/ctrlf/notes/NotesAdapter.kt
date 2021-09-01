package com.thinlineit.ctrlf.notes

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.ListItemNoteBinding
import com.thinlineit.ctrlf.util.BindingRecyclerViewAdapter
import com.thinlineit.ctrlf.util.setBackground

class NotesAdapter(private val clickListener: (Int) -> Unit) :
    RecyclerView.Adapter<NotesAdapter.ViewHolder>(), BindingRecyclerViewAdapter<List<NoteDao>> {
    var noteList: List<NoteDao> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent)

    override fun getItemCount(): Int = noteList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val noteDao = noteList[position]
        holder.bind(noteDao, clickListener, position)
    }

    class ViewHolder(private val dataBinding: ListItemNoteBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {
        fun bind(noteDao: NoteDao, clickListener: (Int) -> Unit, position: Int) {
            val resourceId: Int = when (position % 3) {
                1 -> R.drawable.ic_note_2
                2 -> R.drawable.ic_note_3
                else -> R.drawable.ic_note_1
            }
            dataBinding.apply {
                noteItem.setBackground(resourceId)
                note = noteDao
                root.setOnClickListener {
                    clickListener(noteDao.id)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val dataBinding = ListItemNoteBinding.inflate(layoutInflater)
                return ViewHolder(dataBinding)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setData(data: List<NoteDao>) {
        noteList = data
        notifyDataSetChanged()
    }
}
