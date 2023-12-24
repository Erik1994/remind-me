package com.compose.project.remindme.presentation.reminder

import com.compose.project.remindme.presentation.dialog.item.DialogItemData

sealed class ReminderEvent {
    object AddButtonClickEvent : ReminderEvent()
    object DialogCancelClickEvent : ReminderEvent()
    data class DialogCreateClickEvent(val dialogItemData: DialogItemData) : ReminderEvent()
}
