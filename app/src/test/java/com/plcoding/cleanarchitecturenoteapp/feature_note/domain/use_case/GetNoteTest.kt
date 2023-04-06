package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case

import com.google.common.truth.Truth.assertThat
import com.plcoding.cleanarchitecturenoteapp.feature_note.data.repository.FakeNoteRepository
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetNoteTest{

    private lateinit var getNote:GetNote
    private lateinit var fakeNoteRepository: FakeNoteRepository

    @Before
    fun setUp(){
        fakeNoteRepository = FakeNoteRepository()
        getNote = GetNote(fakeNoteRepository)
    }

    @Test
    fun `get the correct note by passing the correct note id`() = runBlocking {
        val note1 = Note(
            name = "test1",
            content = "test",
            timestamp = 10L,
            color = 1,
            id = 1
        )
        val note2 = Note(
            name = "test2",
            content = "test2",
            timestamp = 10L,
            color = 1,
            id = 2
        )

            fakeNoteRepository.insertNote(note1)
            fakeNoteRepository.insertNote(note2)
            val noteRes1 = getNote(1)
            val noteRes2 = getNote(2)
            assertThat(noteRes1).isEqualTo(note1)
            assertThat(noteRes2).isEqualTo(note2)
    }





}