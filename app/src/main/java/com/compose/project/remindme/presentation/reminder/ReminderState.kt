package com.compose.project.remindme.presentation.reminder

import com.compose.project.remindme.domain.model.Category
import com.compose.project.remindme.presentation.component.item.ItemState
import com.compose.project.remindme.presentation.dialog.item.DialogItemData

data class ReminderState(
    val categorizedItems: List<Category> = emptyList(),
    val showCreateNoteDialog: Boolean = false,
    val dialogItemData: DialogItemData? = null,
    val selectedReminderId: Int? = null
)