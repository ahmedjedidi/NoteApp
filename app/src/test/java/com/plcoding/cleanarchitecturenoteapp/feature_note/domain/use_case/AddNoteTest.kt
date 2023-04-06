package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case

import com.google.common.truth.Truth.assertThat
import com.plcoding.cleanarchitecturenoteapp.feature_note.data.repository.FakeNoteRepository
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.InvalidNoteException
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.utils.NoteOrder
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.utils.OrderType
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class AddNoteTest{

    private lateinit var addNote: AddNote
    private lateinit var fakeNoteRepository: FakeNoteRepository

    @Before
    fun setUp(){
        fakeNoteRepository = FakeNoteRepository()
        addNote = AddNote(fakeNoteRepository)
    }

    @Test
    fun `when name note is blank generate exception`(){
        val note = Note(
            name = "",
            content = "test",
            timestamp = 10L,
            color = 10
        )
        val exception = assertThrows(InvalidNoteException::class.java) {
            runBlocking {
                addNote(note)
            }
        }
        assertThat(exception.message).isEqualTo("The name of note can't be empty.")

    }


    @Test
    fun `when content note is blank generate exception`(){
        val note = Note(
            name = "test",
            content = "",
            timestamp = 10L,
            color = 10
        )
        val exception = assertThrows(InvalidNoteException::class.java) {
            runBlocking {
                addNote(note)
            }
        }
        assertThat(exception.message).isEqualTo("The content of note can't be empty.")
    }

    @Test
    fun `when name and content are not blank note is inserted`(){
        lateinit var notes : List<Note>
        val note = Note(
            name = "test",
            content = "test",
            timestamp = 10L,
            color = 10
        )
            runBlocking {
                addNote(note)
                notes =  fakeNoteRepository.getNotes().first()
            }

        assertThat(notes).contains(note)

    }
}