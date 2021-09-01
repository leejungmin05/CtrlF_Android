package com.thinlineit.ctrlf.notes

import com.google.gson.annotations.SerializedName

data class NoteListDao(
    @SerializedName("next_cursor")
    val nextCursor: Int,
    @SerializedName("notes")
    var notes: List<NoteDao>? = null
)
