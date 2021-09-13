package com.thinlineit.ctrlf.data.request

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    val email: String,
    val code: String,
    val nickname: String,
    val password: String,
    @SerializedName("password_confirm")
    val passwordConfirm: String
)
