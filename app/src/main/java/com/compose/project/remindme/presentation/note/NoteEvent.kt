package com.compose.project.remindme.presentation.note

import com.compose.project.remindme.presentation.dialog.item.DialogItemData

sealed class NoteEvent {
    object AddButtonClickEvent : NoteEvent()
    object DialogCancelClickEvent : NoteEvent()
    data class DialogCreateClickEvent(val dialogItemData: DialogItemData) : NoteEvent()
}
