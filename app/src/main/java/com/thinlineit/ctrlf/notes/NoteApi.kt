package com.thinlineit.ctrlf.notes

import retrofit2.http.*

interface NoteApi {

    // 모든 노트들을 조회, (search) 쿼리 값에 따라 결과 값을 걸러냄
    @GET("notes")
    suspend fun listNote(
        @Query("search") search: String
    ): List<NoteDao>

    //해당하는 노트에 대한 모든 하위의 정보들까지 모두 조회
    @GET("notes/{note_id}")
    suspend fun getSelectNote(
        @Path("note_id") noteId: Int,
    ): NoteDao

    //note 생성
    @FormUrlEncoded
    @POST("notes")
    suspend fun addNote(
        @Field("title") title: String
    )

    //note 수정
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