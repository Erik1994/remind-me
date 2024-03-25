package com.compose.project.remindme.presentation.note

import com.compose.project.remindme.presentation.dialog.item.DialogItemData

sealed class NoteEvent {
    object AddButtonClickEvent : NoteEvent()
    object DialogCancelClickEvent : NoteEvent()
    data class OnLockClickEvent(val id: Int?): NoteEvent()
    data class DialogCreateClickEvent(val dialogItemData: DialogItemData) : NoteEvent()
}
