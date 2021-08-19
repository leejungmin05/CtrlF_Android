package com.thinlineit.ctrlf.network

import com.thinlineit.ctrlf.network.api.TopicApi
import com.thinlineit.ctrlf.network.api.UserApi
import com.thinlineit.ctrlf.notes.NoteApi
import com.thinlineit.ctrlf.page.PageApi
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

object NoteService {
    val retrofitService: NoteApi by lazy {
        retrofit.create(NoteApi::class.java)
    }
}

object TopicService {
    val retrofitService: TopicApi by lazy {
        retrofit.create(TopicApi::class.java)
    }
}

object PageService {
    val retrofitService: PageApi by lazy {
        retrofit.create(PageApi::class.java)
    }
}