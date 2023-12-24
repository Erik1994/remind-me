package com.compose.project.remindme.presentation.note

import com.compose.project.remindme.domain.model.Category
import com.compose.project.remindme.presentation.dialog.item.DialogItemData

data class NoteState(
    val categorizedItems: List<Category> = emptyList(),
    val showCreateNoteDialog: Boolean = false,
    val dialogItemData: DialogItemData? = null,
    val selectedNoteId: Int? = null
)