package com.compose.project.remindme.data.mapper

import androidx.compose.ui.graphics.toArgb
import com.compose.project.remindme.core.util.Mapper
import com.compose.project.remindme.data.local.db.entity.ArchivedEntity
import com.compose.project.remindme.data.local.db.entity.NoteEntity
import com.compose.project.remindme.data.local.db.entity.ReminderEntity
import com.compose.project.remindme.domain.model.ArchivedData
import com.compose.project.remindme.domain.model.NoteData
import com.compose.project.remindme.domain.model.ReminderData

val NOTE_DATA_TO_ENTITY_MAPPER = Mapper<NoteData, NoteEntity> { s ->
    s.run {
        NoteEntity(
            id = id,
            title = title,
            description = description,
            year = localDate.year,
            month = localDate.monthValue,
            dayOfMonth = localDate.dayOfMonth,
            hour = localDate.hour,
            minute = localDate.minute,
            color = color.toArgb(),
            isLocked = isLocked
        )
    }
}

val REMINDER_DATA_TO_ENTITY_MAPPER = Mapper<ReminderData, ReminderEntity> { s ->
    s.run {
        ReminderEntity(
            id = id,
            title = title,
            description = description,
            year = localDate.year,
            month = localDate.monthValue,
            dayOfMonth = localDate.dayOfMonth,
            hour = localDate.hour,
            minute = localDate.minute,
            isCompleted = isCompleted,
            color = color.toArgb(),
            isLocked = isLocked
        )
    }
}

val ARCHIVED_DATA_TO_ENTITY_MAPPER = Mapper<ArchivedData, ArchivedEntity> { s ->
    s.run {
        ArchivedEntity(
            id = id,
            title = title,
            description = description,
            year = localDate.year,
            month = localDate.monthValue,
            dayOfMonth = localDate.dayOfMonth,
            hour = localDate.hour,
            minute = localDate.minute,
            isCompleted = isCompleted,
            color = color.toArgb(),
            isLocked = isLocked
        )
    }
}