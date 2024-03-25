package com.compose.project.remindme.presentation.archived

import com.compose.project.remindme.presentation.dialog.item.DialogItemData
import com.compose.project.remindme.presentation.note.NoteEvent

sealed class ArchivedEvent {
    object DialogCancelClickEvent : ArchivedEvent()
    data class OnLockClickEvent(val id: Int?): ArchivedEvent()
    data class DialogCreateClickEvent(val dialogItemData: DialogItemData) : ArchivedEvent()
}
