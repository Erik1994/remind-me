package com.compose.project.remindme.data.mapper

import com.compose.project.remindme.core.util.Mapper
import com.compose.project.remindme.domain.model.ArchivedData
import com.compose.project.remindme.domain.model.ReminderData

val REMINDER_DATA_TO_ARCHIVED_MAPPER = Mapper<ReminderData, ArchivedData> { s ->
    s.run {
        ArchivedData(
            id = id,
            color = color,
            title = title,
            description = description,
            localDate = localDate,
            isCompleted = true,
            isLocked = isLocked
        )
    }
}