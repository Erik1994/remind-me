package com.compose.project.remindme.domain

import com.compose.project.remindme.domain.model.ReminderData

interface ScheduleReminderUseCase {
    suspend operator fun invoke(id : Int?)
}