package com.compose.project.remindme.domain

import com.compose.project.remindme.domain.model.ReminderData

interface InsertReminderDataUseCase {
    suspend operator fun invoke(data: ReminderData)
}