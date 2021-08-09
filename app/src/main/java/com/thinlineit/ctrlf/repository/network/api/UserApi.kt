package com.thinlineit.ctrlf.repository.network.api

import com.thinlineit.ctrlf.data.request.AuthEmailRequest
import com.thinlineit.ctrlf.data.request.SignUpRequest
import com.thinlineit.ctrlf.data.response.*
import com.thinlineit.ctrlf.model.User
import retrofit2.http.*

interface UserApi {

    @POST("auth/login")
    suspend fun requestLogin(
        @Body request: User
    ): LoginResponse

    @POST("auth/signup")
    suspend fun requestSignUp(
        @Body request: SignUpRequest
    ): SignUpResponse

    @POST("auth/signup/email")
    suspend fun authEmail(
        @Body request: AuthEmailRequest
    ): AuthEmailResponse

    @GET("auth/signup/nickname/duplicate")
    suspend fun checkNickname(@Query("data") data: String): EmailCheckResponse

    @GET("auth/signup/email/duplicate")
    suspend fun checkEmail(@Query("data") data: String): EmailCheckResponse
}