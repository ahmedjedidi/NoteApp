package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case

import com.google.common.truth.Truth.assertThat
import com.plcoding.cleanarchitecturenoteapp.feature_note.data.repository.FakeNoteRepository
import com.plcoding.cleanarchitecturenoteapp.feature_note.data.repository.NoteRepositoryImpl
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.utils.NoteOrder
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.utils.OrderType
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetNotesTest{


    private lateinit var getNotes: GetNotes
    private lateinit var fakeNoteRepository: FakeNoteRepository

    @Before
    fun setUp(){
        fakeNoteRepository = FakeNoteRepository()
        getNotes = GetNotes(fakeNoteRepository)

        val notesToInsert = mutableListOf<Note>()

        ('a'..'z').forEachIndexed { index, c ->
            notesToInsert.add(
                Note(
                    name = c.toString(),
                    content = c.toString(),
                    timestamp = index.toLong(),
                    color = index
                )
            )
        }
        notesToInsert.shuffle()
        runBlocking {
            notesToInsert.forEach {
                fakeNoteRepository.insertNote(it)
            }
        }

    }

    @Test
    fun `Order Notes By title ascending , correct order`()= runBlocking{
        val notes = getNotes(NoteOrder.Title(OrderType.Ascending)).first()
        for (i in 0..notes.size-2){
            assertThat(notes[i].name).isLessThan(notes[i+1].name)
        }
    }

    @Test
    fun `Order Notes By title descending , correct order`()= runBlocking{
        val notes = getNotes(NoteOrder.Title(OrderType.Descending)).first()
        for (i in 0..notes.size-2){
            assertThat(notes[i].name).isGreaterThan(notes[i+1].name)
        }
    }

    @Test
    fun `Order Notes By date ascending , correct order`()= runBlocking{
        val notes = getNotes(NoteOrder.Title(OrderType.Ascending)).first()
        for (i in 0..notes.size-2){
            assertThat(notes[i].timestamp).isLessThan(notes[i+1].timestamp)
        }
    }

    @Test
    fun `Order Notes By date descending , correct order`()= runBlocking{
        val notes = getNotes(NoteOrder.Title(OrderType.Descending)).first()
        for (i in 0..notes.size-2){
            assertThat(notes[i].timestamp).isGreaterThan(notes[i+1].timestamp)
        }
    }

    @Test
    fun `Order Notes By color ascending , correct order`()= runBlocking{
        val notes = getNotes(NoteOrder.Title(OrderType.Ascending)).first()
        for (i in 0..notes.size-2){
            assertThat(notes[i].color).isLessThan(notes[i+1].color)
        }
    }

    @Test
    fun `Order Notes By color descending , correct order`()= runBlocking{
        val notes = getNotes(NoteOrder.Title(OrderType.Descending)).first()
        for (i in 0..notes.size-2){
            assertThat(notes[i].color).isGreaterThan(notes[i+1].color)
        }
    }


}