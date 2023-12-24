package com.compose.project.remindme.data.manager.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.compose.project.remindme.core.util.orDefault
import com.compose.project.remindme.data.repository.ReminderDataRepository
import com.compose.project.remindme.domain.model.ReminderData
import com.compose.project.remindme.receiver.AlarmReceiver
import java.time.ZoneId

class ReminderAlarmManagerImpl(
    private val context: Context,
    private val reminderDataRepository: ReminderDataRepository
) : ReminderAlarmManager {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    override suspend fun schedule(id: Int?) {
        val data = id?.let {
            reminderDataRepository.getReminderEntityById(it)
        } ?: run {
            reminderDataRepository.getAllReminderEntities().last()
        }
        val intent = Intent(
            context,
            AlarmReceiver::class.java
        ).apply {
            putExtra(REMINDER_DATA_ID_KEY, data.id)
            putExtra(REMINDER_DATA_TITLE_KEY, data.title)
            putExtra(REMINDER_DATA_DESCRIPTION_KEY, data.description)
        }
        val reminderId = data.id.orDefault(-1)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            reminderId.hashCode(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        try {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                data.localDate.atZone(ZoneId.systemDefault()).toEpochSecond() * 1000,
                pendingIntent
            )
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    override suspend fun cancel(reminderData: ReminderData) {
        val id = reminderData.id.orDefault(-1)
        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                id.hashCode(),
                Intent(context, AlarmReceiver::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }

    companion object {
        const val REMINDER_DATA_ID_KEY = "reminder_data_key"
        const val REMINDER_DATA_TITLE_KEY = "reminder_data_title_key"
        const val REMINDER_DATA_DESCRIPTION_KEY = "reinder_data_description_key"
    }
}