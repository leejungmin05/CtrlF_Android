package com.thinlineit.ctrlf.notes

import android.annotation.SuppressLint
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.ListItemMainNoteBinding
import com.thinlineit.ctrlf.databinding.ListItemNoteBinding
import com.thinlineit.ctrlf.util.BindingRecyclerViewAdapter

class NotesAdapter(private val viewType: Int, private val clickListener: (Int) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), BindingRecyclerViewAdapter<List<NoteDao>> {
    var noteList: List<NoteDao> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            TYPE_VERTICAL -> VerticalViewHolder.from(parent)
            else -> HorizontalViewHolder.from(parent)
        }

    override fun getItemCount(): Int = noteList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val noteDao = noteList[position]
        val resource = holder.itemView.resources
        when (viewType) {
            TYPE_VERTICAL -> (holder as VerticalViewHolder).bind(
                noteDao,
                clickListener,
                position,
                resource
            )
            else -> (holder as HorizontalViewHolder).bind(
                noteDao,
                clickListener,
                position,
                resource
            )
        }
    }

    override fun getItemViewType(position: Int): Int = viewType

    class VerticalViewHolder(private val dataBinding: ListItemNoteBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {
        fun bind(
            noteDao: NoteDao,
            clickListener: (Int) -> Unit,
            position: Int,
            resources: Resources
        ) {
            val noteDesignArray = resources.obtainTypedArray(R.array.notes)
            val noteResourceId = noteDesignArray.getResourceId(position % NOTEDESIGN_NUM, 0)
            dataBinding.apply {
                noteItemImage.setImageResource(noteResourceId)
                note = noteDao
                root.setOnClickListener {
                    clickListener(noteDao.id)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): RecyclerView.ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val dataBinding = ListItemNoteBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
                return VerticalViewHolder(dataBinding)
            }
        }
    }

    class HorizontalViewHolder(private val dataBinding: ListItemMainNoteBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {
        fun bind(
            noteDao: NoteDao,
            clickListener: (Int) -> Unit,
            position: Int,
            resources: Resources
        ) {
            val noteDesignArray = resources.obtainTypedArray(R.array.notes)
            val noteResourceId = noteDesignArray.getResourceId(position % NOTEDESIGN_NUM, 0)
            dataBinding.apply {
                noteItemImage.setImageResource(noteResourceId)
                note = noteDao
                root.setOnClickListener {
                    clickListener(noteDao.id)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): RecyclerView.ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val dataBinding = ListItemMainNoteBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
                return HorizontalViewHolder(dataBinding)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setData(data: List<NoteDao>) {
        noteList = data
        notifyDataSetChanged()
    }

    companion object {
        const val NOTEDESIGN_NUM = 15
        const val TYPE_VERTICAL = 1
        const val TYPE_HORIZONTAL = 2
    }
}
