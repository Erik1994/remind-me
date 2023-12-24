package com.compose.project.remindme.domain

import com.compose.project.remindme.data.manager.alarm.ReminderAlarmManager
import com.compose.project.remindme.domain.model.ReminderData
import javax.inject.Inject

class CancelScheduledReminderUseCaseImpl @Inject constructor(
    private val reminderAlarmManager: ReminderAlarmManager
) : CancelScheduledReminderUseCase{
    override suspend fun invoke(reminderData: ReminderData) {
        reminderAlarmManager.cancel(reminderData)
    }
}