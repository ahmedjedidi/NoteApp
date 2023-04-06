package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case

import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import com.plcoding.cleanarchitecturenoteapp.feature_note.data.repository.FakeNoteRepository
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DeleteNoteTest{

    private lateinit var deleteNote: DeleteNote
    private lateinit var fakeNoteRepository: FakeNoteRepository

    @Before
    fun setup(){
        fakeNoteRepository = FakeNoteRepository()
        deleteNote = DeleteNote(fakeNoteRepository)
    }

    @Test
    fun `when delete note is called note is deleted`(){
        lateinit var notes : List<Note>
        val note = Note(
            name = "test",
            content = "test",
            timestamp = 10L,
            color = 10
        )
        runBlocking {
            fakeNoteRepository.insertNote(note)
            deleteNote(note)
            notes =  fakeNoteRepository.getNotes().first()
        }

        assertThat(notes).doesNotContain(note)

    }
}