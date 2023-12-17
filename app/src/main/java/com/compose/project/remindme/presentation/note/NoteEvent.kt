package com.compose.project.remindme.presentation.note

import com.compose.project.remindme.domain.model.NoteData
import com.compose.project.remindme.presentation.dialog.itemdialog.DialogItemData

sealed class NoteEvent {
    object AddButtonClickEvent : NoteEvent()
    object DialogCancelClickEvent : NoteEvent()
    data class ItemClickEvent(val noteData: NoteData) : NoteEvent()
    data class DialogCreateClickEvent(val dialogItemData: DialogItemData) : NoteEvent()
    data class ItemDeleteClickEvent(val noteData: NoteData) : NoteEvent()
}
