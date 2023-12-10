package com.compose.project.remindme.presentation.reminder

sealed class ReminderEvent {
    object AddButtonClickEvent : ReminderEvent()
    object DialogCancelClickEvent : ReminderEvent()
}
