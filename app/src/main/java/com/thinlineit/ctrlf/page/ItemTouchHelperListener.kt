package com.thinlineit.ctrlf.page

import androidx.recyclerview.widget.RecyclerView

interface ItemTouchHelperListener {
    fun onItemSwipe(position : Int)
    fun onCorrectionClick(position: Int, viewHolder: RecyclerView.ViewHolder?)
    fun onDeleteClick(position: Int, viewHolder: RecyclerView.ViewHolder?)
}
