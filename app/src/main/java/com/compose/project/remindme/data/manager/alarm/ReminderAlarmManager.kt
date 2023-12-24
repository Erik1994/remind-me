package com.compose.project.remindme.data.manager.alarm

import com.compose.project.remindme.domain.model.ReminderData

interface ReminderAlarmManager {
    suspend fun schedule(id : Int?)
    suspend fun cancel(reminderData: ReminderData)
}