package com.compose.project.remindme.presentation.dialog.itemdialog

import androidx.compose.ui.graphics.Color
import java.time.LocalDateTime

sealed class ItemDialogEvent {
    object CreateEvent : ItemDialogEvent()
    object DismissEvent : ItemDialogEvent()
    object DateTimeClickedEvent : ItemDialogEvent()
    object DateTimePickerCancelEvent: ItemDialogEvent()
    data class ColorSelectionChangedEvent(val color: Color) : ItemDialogEvent()
    data class ItemTitleChangedEvent(val title: String) : ItemDialogEvent()
    data class ItemDescriptionChangedEvent(val description: String) : ItemDialogEvent()
    data class DateTimeChangedEvent(val dateTime: LocalDateTime) : ItemDialogEvent()
}
