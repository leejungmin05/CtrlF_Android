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

object PageService {
    val retrofitService: PageApi by lazy {
        retrofit.create(PageApi::class.java)
    }
}

object NoteService {
    val retrofitService: NoteApi by lazy {
        retrofit.create(NoteApi::class.java)
    }
}

object IssueService {
    val retrofitService: IssueApi by lazy {
        retrofit.create(IssueApi::class.java)
    }
}

object TopicService {
    val retrofitService: TopicApi by lazy {
        retrofit.create(TopicApi::class.java)
    }
}