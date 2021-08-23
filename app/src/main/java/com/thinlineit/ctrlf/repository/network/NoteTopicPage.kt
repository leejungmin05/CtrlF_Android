package com.thinlineit.ctrlf.repository.network

import com.thinlineit.ctrlf.issue.IssueApi
import com.thinlineit.ctrlf.notes.NoteApi
import com.thinlineit.ctrlf.page.PageApi
import com.thinlineit.ctrlf.page.TopicApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//TODO : Fix BASE URL To "http://testdeploy-dev.ap-northeast-2.elasticbeanstalk.com/api/"
private const val BASE_URL = "https://thkwon.pythonanywhere.com/api/"

private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

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