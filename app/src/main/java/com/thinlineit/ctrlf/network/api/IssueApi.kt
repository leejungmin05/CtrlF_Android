package com.thinlineit.ctrlf.network.api

import com.thinlineit.ctrlf.notes.IssueDao
import retrofit2.Call
import retrofit2.http.*

interface IssueApi {

    //모든 이슈들을 조회, (search, note_id, topic_id, limit) 쿼리 값에 따라 결과 값을 걸러냄
    @GET("issues")
    fun getAllIssue(
        @Field("search") search: String,
        @Field("note_id") note_id: String,
        @Field("topic_id") topic_id: String,
        @Field("limit") limit: Int
    ): Call<List<IssueDao>>


    // Issue 생성
    @FormUrlEncoded
    @POST("issues")
    fun postAddIssue(
        @Field("title") title: String,
        @Field("note_id") note_id: Int,
        @Field("topic_id") topic_id: Int,
        @Field("content") content: String
    ): Call<Void>



    //Issue 수정
    @FormUrlEncoded
    @PATCH("issues/{issue_id}")
    fun patchModifiyIssue(
        @Path("issue_id") issueId : Int,
        @Field("title") title: String,
        @Field("note_id") note_id: Int,
        @Field("topic_id") topic_id: Int,
        @Field("content") content: String
    ): Call<Void>



    //Issue 삭제
    @DELETE("issues/{issue_id}")
    fun deleteIssue(
        @Path("issue_id") issue_id : Int
    ): Call<Void>
}