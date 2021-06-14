package com.thinlineit.ctrlf.network.api

import com.thinlineit.ctrlf.notes.TopicDao
import retrofit2.Call
import retrofit2.http.*

interface TopicApi {


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