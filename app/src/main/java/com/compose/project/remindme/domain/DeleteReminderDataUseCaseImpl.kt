package com.compose.project.remindme.domain

import com.compose.project.remindme.data.repository.ReminderDataRepository
import com.compose.project.remindme.domain.model.ReminderData
import javax.inject.Inject

class DeleteReminderDataUseCaseImpl @Inject constructor(
    private val reminderDataRepository: ReminderDataRepository
) : DeleteReminderDataUseCase {
    override suspend fun invoke(data: ReminderData) {
        reminderDataRepository.deleteReminderEntity(data = data)
    }
}