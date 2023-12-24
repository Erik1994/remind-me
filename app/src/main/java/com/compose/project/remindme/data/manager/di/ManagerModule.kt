package com.compose.project.remindme.data.manager.di

import android.app.Application
import com.compose.project.remindme.data.manager.alarm.ReminderAlarmManager
import com.compose.project.remindme.data.manager.alarm.ReminderAlarmManagerImpl
import com.compose.project.remindme.data.manager.notification.ReminderNotificationManager
import com.compose.project.remindme.data.manager.notification.ReminderNotificationManagerImpl
import com.compose.project.remindme.data.repository.ReminderDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ManagerModule {

    @Provides
    @Singleton
    fun provideReminderAlarmManager(
        context: Application,
        reminderDataRepository: ReminderDataRepository
    ): ReminderAlarmManager {
        return ReminderAlarmManagerImpl(
            context = context,
            reminderDataRepository = reminderDataRepository
        )
    }

    @Provides
    @Singleton
    fun provideReminderNotificationManager(
        context: Application
    ): ReminderNotificationManager {
        return ReminderNotificationManagerImpl(
            context = context
        )
    }
}