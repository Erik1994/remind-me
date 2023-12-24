package com.compose.project.remindme.domain

import com.compose.project.remindme.data.repository.ReminderDataRepository
import com.compose.project.remindme.domain.model.ReminderData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllReminderDataUseCaseImpl @Inject constructor(
    private val reminderDataRepository: ReminderDataRepository
) : GetAllReminderDataUseCase {
    override fun invoke(): Flow<List<ReminderData>> {
        return reminderDataRepository.getAllOrderedReminderEntities()
    }
}