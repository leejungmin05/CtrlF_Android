package com.thinlineit.ctrlf.network

import com.thinlineit.ctrlf.network.api.UserApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://thkwon.pythonanywhere.com/api/"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object RegistrationService {
    val USER_API: UserApi by lazy {
        retrofit.create(UserApi::class.java)
    }
}
