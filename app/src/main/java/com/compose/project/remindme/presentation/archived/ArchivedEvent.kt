package com.compose.project.remindme.presentation.archived

import com.compose.project.remindme.presentation.dialog.item.DialogItemData

sealed class ArchivedEvent {
    object DialogCancelClickEvent : ArchivedEvent()
    data class DialogCreateClickEvent(val dialogItemData: DialogItemData) : ArchivedEvent()
}
