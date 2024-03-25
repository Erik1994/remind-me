package com.compose.project.remindme.data.mapper

import com.compose.project.remindme.core.util.Mapper
import com.compose.project.remindme.domain.model.ItemData
import com.compose.project.remindme.domain.model.NoteData
import com.compose.project.remindme.domain.model.ReminderData
import com.compose.project.remindme.presentation.dialog.item.DialogItemData

val DIALOG_ITEM_TO_NOTE_DATA_MAPPER = Mapper<DialogItemData, NoteData> { s ->
    s.run {
        NoteData(
            id = id,
            title = itemTitle,
            description = itemDescription,
            localDate = dateTime,
            color = color,
            isLocked = isLocked
        )
    }
}

val DIALOG_ITEM_TO_REMINDER_DATA_MAPPER = Mapper<DialogItemData, ReminderData> { s ->
    s.run {
        ReminderData(
            title = itemTitle,
            description = itemDescription,
            localDate = dateTime,
            color = color,
            isLocked = isLocked
        )
    }
}

val ITEM_DATA_TO_DIALOG_ITEM_MAPPER = Mapper<ItemData, DialogItemData> { s ->
    s.run {
        DialogItemData(
            id = id,
            itemTitle = title,
            itemDescription = description,
            dateTime = localDate,
            color = color,
            isLocked = isLocked
        )
    }
}