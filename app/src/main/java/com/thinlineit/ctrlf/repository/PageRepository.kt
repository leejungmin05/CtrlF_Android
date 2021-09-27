package com.thinlineit.ctrlf.repository

import com.thinlineit.ctrlf.notes.NoteDao
import com.thinlineit.ctrlf.notes.TopicDao
import com.thinlineit.ctrlf.page.PageDao
import com.thinlineit.ctrlf.repository.network.NoteService
import com.thinlineit.ctrlf.repository.network.PageService
import com.thinlineit.ctrlf.repository.network.TopicService

class PageRepository {

    suspend fun loadPage(pageId: Int): PageDao {
        return PageService.retrofitService.getPage(pageId.toString())
    }

    suspend fun loadNoteInfo(noteId: String): List<TopicDao> {
        return NoteService.retrofitService.getNote(noteId)
    }

    suspend fun loadNoteDetailInfo(noteId: String): NoteDao {
        return NoteService.retrofitService.getNoteDetail(Integer.parseInt(noteId))
    }

    suspend fun loadPageList(topicId: Int): List<PageDao> {
        return TopicService.retrofitService.getPageList(topicId.toString())
    }
}
