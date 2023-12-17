package com.compose.project.remindme.data.mapper

import com.compose.project.remindme.core.util.Mapper
import com.compose.project.remindme.domain.model.NoteData
import com.compose.project.remindme.domain.model.ReminderData
import com.compose.project.remindme.presentation.dialog.itemdialog.DialogItemData

val DIALOG_ITEM_TO_NOTE_DATA_MAPPER = Mapper<DialogItemData, NoteData> { s ->
    s.run {
        NoteData(
            id = id,
            title = itemTitle,
            description = itemDescription,
            localDate = dateTime,
            color = color
        )
    }
}

val DIALOG_ITEM_TO_REMINDER_DATA_MAPPER = Mapper<DialogItemData, ReminderData> { s ->
    s.run {
        ReminderData(
            title = itemTitle,
            description = itemDescription,
            localDate = dateTime,
            color = color
        )
    }
}

val NOTE_DATA_TO_DIALOG_ITEM_MAPPER = Mapper<NoteData, DialogItemData> { s ->
    s.run {
        DialogItemData(
            id = id,
            itemTitle = title,
            itemDescription = description,
            dateTime = localDate,
            color = color
        )
    }
}

val REMINDER_DATA_TO_DIALOG_ITEM_MAPPER = Mapper<ReminderData, DialogItemData> { s ->
    s.run {
        DialogItemData(
            itemTitle = title,
            itemDescription = description,
            dateTime = localDate,
            color = color
        )
    }
}