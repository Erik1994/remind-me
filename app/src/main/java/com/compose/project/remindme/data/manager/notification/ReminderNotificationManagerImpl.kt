package com.compose.project.remindme.data.manager.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.compose.project.remindme.R
import com.compose.project.remindme.activity.MainActivity
import com.compose.project.remindme.data.manager.notification.ReminderNotificationManager.Companion.ARCHIVED_ROUT_KEY
import com.compose.project.remindme.data.manager.notification.ReminderNotificationManager.Companion.REINDER_CHANNEL_ID
import com.compose.project.remindme.data.manager.notification.ReminderNotificationManager.Companion.REMINDER_NOTIFICATION_CLICK_ACTION
import com.compose.project.remindme.presentation.navigation.Route

class ReminderNotificationManagerImpl(
    private val context: Context
) : ReminderNotificationManager {

    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    override fun showNotification(
        title: String,
        description: String
    ) {
        val activityIntent = Intent(context, MainActivity::class.java).apply {
            action = REMINDER_NOTIFICATION_CLICK_ACTION
            putExtra(ARCHIVED_ROUT_KEY, Route.ARCHIVED)
        }
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_IMMUTABLE
        )
        val notification = NotificationCompat.Builder(context, REINDER_CHANNEL_ID)
            .setSmallIcon(R.drawable.reminder_icon)
            .setContentTitle(title)
            .setContentText(description)
            .setAutoCancel(true)
            .setContentIntent(activityPendingIntent)
            .build()

        notificationManager.notify(1, notification)
    }
}