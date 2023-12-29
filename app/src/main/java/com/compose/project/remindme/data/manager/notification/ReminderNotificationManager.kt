package com.compose.project.remindme.data.manager.notification

interface ReminderNotificationManager {
    fun showNotification(
        title: String,
        description: String
    )

    companion object {
        const val REINDER_CHANNEL_NAME = "Reminder"
        const val REINDER_CHANNEL_ID = "REMINDER_ID"
        const val REMINDER_NOTIFICATION_CLICK_ACTION = "reminder_not_click_action"
        const val ARCHIVED_ROUT_KEY = "archived_route"
    }
}