package com.thinlineit.ctrlf.notes

import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface NoteApi {

    // 해당 노트 detail
    @GET("notes/{note_id}")
    suspend fun getNoteDetail(
        @Path("note_id") noteId: Int
    ): NoteDao

    // 모든 노트들을 조회, (search) 쿼리 값에 따라 결과 값을 걸러냄
    @GET("notes")
    suspend fun listNote(
        @Query("cursor") cursor: Int
    ): NoteListDao

    // 해당하는 노트에 대한 모든 하위의 정보들까지 모두 조회
    @GET("notes/{note_id}/topics")
    suspend fun getNote(
        @Path("note_id") noteId: String
    ): List<TopicDao>

    // note 생성
    @FormUrlEncoded
    @POST("notes")
    suspend fun addNote(
        @Field("title") title: String
    )

    // note 수정
    @FormUrlEncoded
    @PATCH("notes/{note_id}")
    suspend fun updateNote(
        @Path("note_id") noteId: Int,
        @Field("title") title: String
    )

    @DELETE("notes/{note_id}")
    suspend fun deleteNote(
        @Path("note_id") noteId: Int
    )
}
