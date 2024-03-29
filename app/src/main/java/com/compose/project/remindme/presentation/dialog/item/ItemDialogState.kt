package com.compose.project.remindme.presentation.dialog.item

import com.compose.project.remindme.presentation.component.color.ColorItemData
import com.compose.project.remindme.presentation.component.color.ColorItemList
import java.time.LocalDateTime

data class ItemDialogState(
    val colorItems: List<ColorItemData> = emptyList(),
    val dialogType: ItemDialogType = ItemDialogType.NoteDialog,
    val dateTime: LocalDateTime = LocalDateTime.now(),
    val itemTitle: String = "",
    val itemDescription: String = "",
    val shouldShowErrorMessage: Boolean = false,
    val needSetDefaultValue: Boolean = true,
    val needShowDateTimePicker: Boolean = false,
    val isLockSwitchChecked: Boolean = false
)
