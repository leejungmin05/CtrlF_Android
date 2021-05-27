package com.thinlineit.ctrlf.page

import com.thinlineit.ctrlf.page.PageDao
import retrofit2.Call
import retrofit2.http.*

interface PageApi{

    //해당하는 페이지에 대한 정보를 조회
    @GET("pages/{page_id}")
    fun getSelcetPage(
        @Path("page_id") num : Int,
    ): Call<PageDao>



    //Page 생성
    @FormUrlEncoded
    @POST("pages")
    fun postAddPages(
        @Field("topic_id") topic_id: Int,
        @Field("title") title: String,
        @Field("content") content: String
    ): Call<Void>

    //Page 수정
    @FormUrlEncoded
    @PATCH("pages/{page_id}")
    fun patchModifyPage(
        @Path("page_id") page_id : Int,
        @Field("topic_id") topic_id: Int,
        @Field("title") title: String,
        @Field("content") content: String
    ): Call<Void>

    @DELETE("pages/{page_id}")
    fun deletePage(
        @Path("page_id") page_id : Int
    ): Call<Void>



}