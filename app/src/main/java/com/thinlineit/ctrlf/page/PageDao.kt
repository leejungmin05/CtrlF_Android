package com.thinlineit.ctrlf.page

import com.google.gson.annotations.SerializedName

data class PageDao(
    val id: Int,
    val title: String? = null,
    val content: String? = null,
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("is_approved")
    val isApproved: Boolean? = null,
    val owners : List<Int>? = null
)
