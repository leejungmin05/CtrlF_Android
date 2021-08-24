package com.thinlineit.ctrlf.notes

import com.google.gson.annotations.SerializedName
import com.thinlineit.ctrlf.page.PageDao

data class NoteDao(
    val id: Int,
    val title: String,
    @SerializedName("is_approved")
    val isApproved: Boolean
)

data class TopicDao(
    val id: Int,
    val title: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("is_approved")
    val isApproved: Boolean,
    @SerializedName("owner_id")
    val ownerId: Int
)



