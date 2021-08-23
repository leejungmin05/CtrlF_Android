package com.thinlineit.ctrlf.repository.network

import com.thinlineit.ctrlf.issue.IssueApi
import com.thinlineit.ctrlf.notes.NoteApi
import com.thinlineit.ctrlf.page.PageApi
import com.thinlineit.ctrlf.page.TopicApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL =
    "http://kongjingoo.pythonanywhere.com/api/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

object IssueService {
    val retrofitService: IssueApi by lazy {
        retrofit.create(IssueApi::class.java)
    }
}
