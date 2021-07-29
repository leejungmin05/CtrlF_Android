package com.thinlineit.ctrlf.notes

import com.google.gson.annotations.SerializedName
import com.thinlineit.ctrlf.page.PageDao

data class NoteDao(
    val id: Int,
    val title: String,
    @SerializedName("topics")
    var topicList: List<TopicDao>? = null
)

data class TopicDao(
    val id: Int,
    val title: String,
    @SerializedName("note_id")
    val noteId: Int? = null,
    @SerializedName("pages")
    val pageList: List<PageDao>? = null
)



