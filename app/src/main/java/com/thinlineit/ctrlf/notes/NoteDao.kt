package com.thinlineit.ctrlf.notes

import com.google.gson.annotations.SerializedName
import com.thinlineit.ctrlf.page.PageDao

data class NoteDao(
    val id: Int,
    val title: String,
    val is_approved: Boolean
)

data class TopicDao(
    val id: Int,
    val title: String,
    val created_at: String,
    val is_approved: Boolean,
    val owner_id: Int
)



