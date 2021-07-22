package com.thinlineit.ctrlf.data.request

data class SignUpRequest(
    val email: String,
    val code: String,
    val nickname: String,
    val password: String,
    val password_confirm: String
) : Request
