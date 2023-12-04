package com.compose.project.remindme.domain

import com.compose.project.remindme.domain.model.ReminderData
import kotlinx.coroutines.flow.Flow

interface GetAllReminderDataUseCase {
    suspend operator fun invoke(): Flow<List<ReminderData>>
}