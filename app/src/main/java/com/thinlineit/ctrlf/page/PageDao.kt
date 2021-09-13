package com.thinlineit.ctrlf.page

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PageDao(
    val id: Int? = null,
    val title: String? = null,
    val content: String? = null,
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("is_approved")
    val isApproved: Boolean? = null,
    val owners: List<Int>? = null
) : Parcelable
