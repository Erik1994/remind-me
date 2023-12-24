package com.compose.project.remindme.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.compose.project.remindme.data.manager.alarm.ReminderAlarmManagerImpl.Companion.REMINDER_DATA_DESCRIPTION_KEY
import com.compose.project.remindme.data.manager.alarm.ReminderAlarmManagerImpl.Companion.REMINDER_DATA_ID_KEY
import com.compose.project.remindme.data.manager.alarm.ReminderAlarmManagerImpl.Companion.REMINDER_DATA_TITLE_KEY
import com.compose.project.remindme.data.manager.notification.ReminderNotificationManager
import com.compose.project.remindme.data.mapper.REMINDER_DATA_TO_ARCHIVED_MAPPER
import com.compose.project.remindme.data.repository.ArchivedDataRepository
import com.compose.project.remindme.data.repository.ReminderDataRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@AndroidEntryPoint
class AlarmReceiver : BroadcastReceiver() {

    @Inject
    lateinit var notificationManager: ReminderNotificationManager

    @Inject
    lateinit var archivedDataRepository: ArchivedDataRepository

    @Inject
    lateinit var reminderDataRepository: ReminderDataRepository

    override fun onReceive(context: Context?, intent: Intent?) {
        goAsync {
            intent?.let {
                val id = it.getIntExtra(REMINDER_DATA_ID_KEY, -1)
                val title = it.getStringExtra(REMINDER_DATA_TITLE_KEY).orEmpty()
                val description = it.getStringExtra(REMINDER_DATA_DESCRIPTION_KEY).orEmpty()
                val reminder = reminderDataRepository.getReminderEntityById(id = id)
                reminder?.let { reminder ->
                    reminderDataRepository.deleteReminderEntity(reminder)
                    archivedDataRepository.insertArchivedEntity(REMINDER_DATA_TO_ARCHIVED_MAPPER.map(reminder))
                }
                notificationManager.showNotification(title, description)
            }
        }
    }
}

fun BroadcastReceiver.goAsync(
    context: CoroutineContext = EmptyCoroutineContext,
    block: suspend CoroutineScope.() -> Unit
) {
    val pendingResult = goAsync()
    GlobalScope.launch {
        try {
            block()
        } finally {
            pendingResult.finish()
        }
    }
}