package com.compose.project.remindme.data.mapper

import com.compose.project.remindme.core.util.Mapper
import com.compose.project.remindme.data.local.db.entity.ArchivedEntity
import com.compose.project.remindme.data.local.db.entity.NoteEntity
import com.compose.project.remindme.data.local.db.entity.ReminderEntity
import com.compose.project.remindme.domain.model.ArchivedData
import com.compose.project.remindme.domain.model.NoteData
import com.compose.project.remindme.domain.model.ReminderData
import java.time.LocalDate
import java.time.LocalDateTime

val NOTE_ENTITY_TO_DATA_MAPPER = Mapper<NoteEntity, NoteData> { s ->
    s.run {
        NoteData(
            id = id,
            title = title,
            description = description,
            localDate = LocalDateTime.of(year, month, dayOfMonth, hour, minute),
            isCompleted = isCompleted
        )
    }
}

val REMINDER_ENTITY_TO_DATA_MAPPER = Mapper<ReminderEntity, ReminderData> { s ->
    s.run {
        ReminderData(
            id = id,
            title = title,
            description = description,
            localDate = LocalDateTime.of(year, month, dayOfMonth, hour, minute),
            isCompleted = isCompleted
        )
    }
}

val ARCHIVED_ENTITY_TO_DATA_MAPPER = Mapper<ArchivedEntity, ArchivedData> { s ->
    s.run {
        ArchivedData(
            id = id,
            title = title,
            description = description,
            localDate = LocalDateTime.of(year, month, dayOfMonth, hour, minute),
            isCompleted = isCompleted
        )
    }
}