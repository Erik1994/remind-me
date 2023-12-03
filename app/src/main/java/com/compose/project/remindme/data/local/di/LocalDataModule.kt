package com.compose.project.remindme.data.local.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.compose.project.remindme.data.local.constant.LocalDataConstants
import com.compose.project.remindme.data.local.db.AppDb
import com.compose.project.remindme.data.local.db.dao.ArchivedDao
import com.compose.project.remindme.data.local.db.dao.NoteDao
import com.compose.project.remindme.data.local.db.dao.ReminderDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {

    @Provides
    @Singleton
    fun provideSharedPref(app: Application): SharedPreferences {
        return app.getSharedPreferences(
            LocalDataConstants.SHARED_PREF_NAME,
            Context.MODE_PRIVATE
        )
    }

    @Provides
    @Singleton
    fun provideAppDb(app: Application): AppDb {
        return Room.databaseBuilder(
            context = app,
            klass = AppDb::class.java,
            name = LocalDataConstants.APP_DB
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteDao(appDb: AppDb): NoteDao {
        return appDb.noteDao
    }

    @Provides
    @Singleton
    fun provideReminderDao(appDb: AppDb): ReminderDao {
        return appDb.reminderDao
    }

    @Provides
    @Singleton
    fun provideArchivedDao(appDb: AppDb): ArchivedDao {
        return appDb.archivedDao
    }
}