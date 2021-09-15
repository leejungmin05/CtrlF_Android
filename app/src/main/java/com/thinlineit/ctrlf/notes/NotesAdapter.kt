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
            val resourceId: Int = when (position % 15) {
                1 -> R.drawable.ic_note_2
                2 -> R.drawable.ic_note_3
                3 -> R.drawable.ic_note_4
                4 -> R.drawable.ic_note_5
                5 -> R.drawable.ic_note_6
                6 -> R.drawable.ic_note_7
                7 -> R.drawable.ic_note_8
                8 -> R.drawable.ic_note_9
                9 -> R.drawable.ic_note_10
                10 -> R.drawable.ic_note_11
                11 -> R.drawable.ic_note_12
                12 -> R.drawable.ic_note_13
                13 -> R.drawable.ic_note_14
                14 -> R.drawable.ic_note_15
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
                val dataBinding = ListItemNoteBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
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
