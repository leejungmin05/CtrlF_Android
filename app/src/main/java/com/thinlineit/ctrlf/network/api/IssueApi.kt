package com.thinlineit.ctrlf.network.api

import com.thinlineit.ctrlf.notes.IssueDao
import retrofit2.Call
import retrofit2.http.*

interface
IssueApi {

    //모든 이슈들을 조회, (search, note_id, topic_id, limit) 쿼리 값에 따라 결과 값을 걸러냄
    @GET("issues")
    suspend fun listIssue(
        @Field("search") search: String,
        @Field("note_id") noteId: String,
        @Field("topic_id") topicId: String,
        @Field("limit") limit: Int
    ): List<IssueDao>


    // Issue 생성
    @FormUrlEncoded
    @POST("issues")
    suspend fun addIssue(
        @Field("title") title: String,
        @Field("note_id") noteId: Int,
        @Field("topic_id") topicId: Int,
        @Field("content") content: String
    )

    //Issue 수정
    @FormUrlEncoded
    @PATCH("issues/{issue_id}")
    suspend fun updateIssue(
        @Path("issue_id") issueId: Int,
        @Field("title") title: String,
        @Field("note_id") noteId: Int,
        @Field("topic_id") topicId: Int,
        @Field("content") content: String
    )


    //Issue 삭제
    @DELETE("issues/{issue_id}")
    suspend fun deleteIssue(
        @Path("issue_id") issueId: Int
    )

}