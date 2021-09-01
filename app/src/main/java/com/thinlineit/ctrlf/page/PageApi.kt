package com.thinlineit.ctrlf.page

import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path


interface PageApi {

    // 해당하는 페이지에 대한 정보를 조회
    @GET("pages/{page_id}")
    suspend fun getPage(
        @Path("page_id") pageId: String,
    ): PageDao

    // Page 생성
    @FormUrlEncoded
    @POST("pages")
    suspend fun addPages(
        @Field("topic_id") topicId: Int,
        @Field("title") title: String,
        @Field("content") content: String
    )

    // Page 수정
    @FormUrlEncoded
    @PATCH("pages/{page_id}")
    suspend fun updatePage(
        @Path("page_id") pageId: Int,
        @Field("topic_id") topicId: Int,
        @Field("title") title: String,
        @Field("content") content: String
    )

    @DELETE("pages/{page_id}")
    suspend fun deletePage(
        @Path("page_id") pageId: Int
    )
}
