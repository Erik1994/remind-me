package com.compose.project.remindme.domain

import com.compose.project.remindme.data.repository.ReminderDataRepository
import com.compose.project.remindme.domain.model.ReminderData
import javax.inject.Inject

class InsertReminderDataUseCaseImpl @Inject constructor(
    private val reminderDataRepository: ReminderDataRepository
) : InsertReminderDataUseCase {
    override suspend fun invoke(data: ReminderData) {
        reminderDataRepository.insertReminderEntity(data = data)
    }
}