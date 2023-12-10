package com.compose.project.remindme.presentation.note

sealed class NoteEvent {
    object AddButtonClickEvent : NoteEvent()
    object CancelClickEvent : NoteEvent()
}
