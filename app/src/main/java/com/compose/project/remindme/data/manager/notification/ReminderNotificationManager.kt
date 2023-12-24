package com.compose.project.remindme.data.manager.notification

interface ReminderNotificationManager {
    fun showNotification(
        title: String,
        description: String
    )

    companion object {
        const val REINDER_CHANNEL_NAME = "Reminder"
        const val REINDER_CHANNEL_ID = "REMINDER_ID"
    }
}