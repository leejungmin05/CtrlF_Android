package com.thinlineit.ctrlf.network.api

import com.thinlineit.ctrlf.notes.TopicDao
import retrofit2.Call
import retrofit2.http.*

interface TopicApi {


    //모든 토픽들을 조회
    @GET("topics")
    fun getAllTopic(
    ): Call<List<TopicDao>>


    //Topic 생성
    @FormUrlEncoded
    @POST("topics")
    fun postAddTopic(
        @Field("note_id") note_id: Int,
        @Field("title") title: String
    ): Call<Void>

    //Topic 수정
    @FormUrlEncoded
    @PATCH("topics/{topic_id}")
    fun patchModifyTopic(
        @Path("topic_id") topic_id : Int,
        @Field("note_id") note_id: Int,
        @Field("title") title: String
    ): Call<Void>


    //Topic 삭제
    @DELETE("topics/{topic_id}")
    fun deleteTopic(
        @Path("topic_id") topic_id : Int
    ): Call<Void>
}