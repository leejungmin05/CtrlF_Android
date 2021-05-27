package com.thinlineit.ctrlf.notes

import com.thinlineit.ctrlf.network.HomeInformation
import retrofit2.Call
import retrofit2.http.*

interface NoteApi {

    //홈 화면의 정보를 조회 => 모든 페이지 개수, 모든 노트 정보, 최근 이슈 6개
    @GET("home")
    fun getHomeInformation(): Call<HomeInformation>

    // 모든 노트들을 조회, (search) 쿼리 값에 따라 결과 값을 걸러냄
    @GET("notes")
    fun getAllNote(
        @Query("search") search: String
    ): Call<List<NoteDao>>

    //해당하는 노트에 대한 모든 하위의 정보들까지 모두 조회
    @GET("notes/{note_id}")
    fun getSelectNote(
        @Path("note_id") num : Int,
    ): Call<NoteDao>

    //note 생성
    @FormUrlEncoded
    @POST("notes")
    fun postAddNote(
        @Field("title") title: String
    ): Call<Void>

    //note 수정
    @FormUrlEncoded
    @PATCH("notes/{note_id}")
    fun patchModifyNote(
        @Path("note_id") note_id : Int,
        @Field("title") title: String
    ): Call<Void>



    @DELETE("notes/{note_id}")
    fun deleteNote(
        @Path("note_id") note_id : Int
    ): Call<Void>
}