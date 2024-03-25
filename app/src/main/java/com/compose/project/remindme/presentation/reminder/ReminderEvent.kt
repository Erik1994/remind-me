package com.compose.project.remindme.presentation.reminder

import com.compose.project.remindme.presentation.dialog.item.DialogItemData
import com.compose.project.remindme.presentation.note.NoteEvent

sealed class ReminderEvent {
    object AddButtonClickEvent : ReminderEvent()
    object DialogCancelClickEvent : ReminderEvent()
    data class OnLockClickEvent(val id: Int?): ReminderEvent()
    data class DialogCreateClickEvent(val dialogItemData: DialogItemData) : ReminderEvent()
}
