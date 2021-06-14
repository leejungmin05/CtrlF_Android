package com.thinlineit.ctrlf.page

import com.thinlineit.ctrlf.page.PageDao
import retrofit2.Call
import retrofit2.http.*

interface PageApi{

    //해당하는 페이지에 대한 정보를 조회
    @GET("pages/{page_id}")
    suspend fun getSelcetPage(
        @Path("page_id") pageId : Int,
    ): PageDao


    //Page 생성
    @FormUrlEncoded
    @POST("pages")
    suspend fun addPages(
        @Field("topic_id") topicId: Int,
        @Field("title") title: String,
        @Field("content") content: String
    )


    //Page 수정
    @FormUrlEncoded
    @PATCH("pages/{page_id}")
    suspend fun updatePage(
        @Path("page_id") pageId : Int,
        @Field("topic_id") topicId: Int,
        @Field("title") title: String,
        @Field("content") content: String
    )


    @DELETE("pages/{page_id}")
    suspend fun deletePage(
        @Path("page_id") pageId : Int
    )



}