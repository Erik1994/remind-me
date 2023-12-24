package com.compose.project.remindme.domain

import com.compose.project.remindme.data.manager.alarm.ReminderAlarmManager
import javax.inject.Inject

class ScheduleReminderUseCaseImpl @Inject constructor(
    private val reminderAlarmManager: ReminderAlarmManager
) : ScheduleReminderUseCase{
    override suspend fun invoke(id: Int?) {
        reminderAlarmManager.schedule(id)
    }
}