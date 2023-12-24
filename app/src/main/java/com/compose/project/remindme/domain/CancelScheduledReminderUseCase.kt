package com.compose.project.remindme.domain

import com.compose.project.remindme.domain.model.ReminderData

interface CancelScheduledReminderUseCase {
    suspend operator fun invoke(reminderData: ReminderData)
}