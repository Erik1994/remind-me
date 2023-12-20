package com.compose.project.remindme.presentation.dialog.item

import com.compose.project.remindme.R
import com.compose.project.remindme.core.util.UiText

sealed class ItemDialogType(val title: UiText) {
    object NoteDialog : ItemDialogType(UiText.StringResource(R.string.create_note))
    object ReminderDialog : ItemDialogType(UiText.StringResource(R.string.create_reminder))
}