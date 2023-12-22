package com.compose.project.remindme.presentation.note

import com.compose.project.remindme.domain.model.NoteData
import com.compose.project.remindme.presentation.component.item.ItemState
import com.compose.project.remindme.presentation.dialog.item.DialogItemData

data class NoteState(
    val itemState: ItemState = ItemState(),
    val showCreateNoteDialog: Boolean = false,
    val dialogItemData: DialogItemData? = null,
    val selectedNoteId: Int? = null
)