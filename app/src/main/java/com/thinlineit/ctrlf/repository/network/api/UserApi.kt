package com.thinlineit.ctrlf.repository.network.api

import com.thinlineit.ctrlf.data.request.AuthEmailRequest
import com.thinlineit.ctrlf.data.request.CodeCheckRequest
import com.thinlineit.ctrlf.data.request.SignUpRequest
import com.thinlineit.ctrlf.data.response.CodeCheckResponse
import com.thinlineit.ctrlf.data.response.EmailCheckResponse
import com.thinlineit.ctrlf.data.response.LoginResponse
import com.thinlineit.ctrlf.data.response.SignUpResponse
import com.thinlineit.ctrlf.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserApi {

    @POST("auth/login/")
    suspend fun requestLogin(
        @Body request: User
    ): LoginResponse

    @POST("auth/signup/")
    suspend fun requestSignUp(
        @Body request: SignUpRequest
    ): SignUpResponse

    @POST("auth/signup/email")
    suspend fun sendingAuthEmail(
        @Body request: AuthEmailRequest
    ): Unit

    @POST("auth/verification-code/check")
    suspend fun requestCodeCheck(
        @Body request: CodeCheckRequest
    ): CodeCheckResponse

    @GET("auth/signup/nickname/duplicate")
    suspend fun checkNickname(@Query("data") data: String): EmailCheckResponse

    @GET("auth/signup/email/duplicate")
    suspend fun checkEmail(@Query("data") data: String): EmailCheckResponse
}
