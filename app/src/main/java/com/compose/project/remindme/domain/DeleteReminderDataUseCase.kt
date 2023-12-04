package com.compose.project.remindme.domain

import com.compose.project.remindme.domain.model.ReminderData

interface DeleteReminderDataUseCase {
    suspend operator fun invoke(data: ReminderData)
}