package com.thinlineit.ctrlf.network.api

import com.thinlineit.ctrlf.notes.TopicDao
import com.thinlineit.ctrlf.page.PageDao
import retrofit2.http.*

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