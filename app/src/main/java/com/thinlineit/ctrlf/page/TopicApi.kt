package com.thinlineit.ctrlf.page

import com.thinlineit.ctrlf.notes.TopicDao
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface TopicApi {

    @GET("topics/{topic_id}/pages")
    suspend fun getPageList(
        @Path("topic_id") topic_id: String,
    ): List<PageDao>

    //모든 토픽들을 조회
    @GET("topics")
    suspend fun listTopic(
    ): List<TopicDao>

    //Topic 생성
    @FormUrlEncoded
    @POST("topics")
    suspend fun addTopic(
        @Field("note_id") noteId: Int,
        @Field("title") title: String
    )

    //Topic 수정
    @FormUrlEncoded
    @PATCH("topics/{topic_id}")
    suspend fun updateTopic(
        @Path("topic_id") topicId: Int,
        @Field("note_id") noteId: Int,
        @Field("title") title: String
    )

    //Topic 삭제
    @DELETE("topics/{topic_id}")
    suspend fun deleteTopic(
        @Path("topic_id") topicId: Int
    )
}