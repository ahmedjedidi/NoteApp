package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.add_edit_note

data class NoteTextFieldState(
    val text:String = "",
    val textHint:String = "",
    val isHintVisible:Boolean = true
)
