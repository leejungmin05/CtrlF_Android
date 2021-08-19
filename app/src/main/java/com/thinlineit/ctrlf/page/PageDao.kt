package com.thinlineit.ctrlf.page

data class PageDao(
    val id: Int,
    val title: String? = null,
    val content: String? = null,
    val created_at: String? = null,
    val is_approved: Boolean? = null,
    val owners : List<Int>? = null
)
