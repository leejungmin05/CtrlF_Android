package com.thinlineit.ctrlf.notes

data class AllNoteDao(
    val next_cursor: Int,
    var notes: List<NoteDao>? = null
)

