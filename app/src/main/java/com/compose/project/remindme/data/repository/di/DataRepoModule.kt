package com.compose.project.remindme.data.repository.di

import com.compose.project.remindme.data.local.db.dao.ArchivedDao
import com.compose.project.remindme.data.local.db.dao.NoteDao
import com.compose.project.remindme.data.local.db.dao.ReminderDao
import com.compose.project.remindme.data.mapper.ARCHIVED_DATA_TO_ENTITY_MAPPER
import com.compose.project.remindme.data.mapper.ARCHIVED_ENTITY_TO_DATA_MAPPER
import com.compose.project.remindme.data.mapper.NOTE_DATA_TO_ENTITY_MAPPER
import com.compose.project.remindme.data.mapper.NOTE_ENTITY_TO_DATA_MAPPER
import com.compose.project.remindme.data.mapper.REMINDER_DATA_TO_ENTITY_MAPPER
import com.compose.project.remindme.data.mapper.REMINDER_ENTITY_TO_DATA_MAPPER
import com.compose.project.remindme.data.repository.ArchivedDataRepository
import com.compose.project.remindme.data.repository.ArchivedDataRepositoryImpl
import com.compose.project.remindme.data.repository.NoteDataRepository
import com.compose.project.remindme.data.repository.NoteDataRepositoryImpl
import com.compose.project.remindme.data.repository.ReminderDataRepository
import com.compose.project.remindme.data.repository.ReminderDataRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataRepoModule {

    @Provides
    @Singleton
    fun provideArchivedDataRepository(archivedDao: ArchivedDao): ArchivedDataRepository {
        return ArchivedDataRepositoryImpl(
            archivedDao = archivedDao,
            entityToDataMapper = ARCHIVED_ENTITY_TO_DATA_MAPPER,
            dataToEntityMapper = ARCHIVED_DATA_TO_ENTITY_MAPPER
        )
    }

    @Provides
    @Singleton
    fun provideNoteDataRepository(noteDao: NoteDao): NoteDataRepository {
        return NoteDataRepositoryImpl(
            noteDao = noteDao,
            dataToEntityMapper = NOTE_DATA_TO_ENTITY_MAPPER,
            entityToDataMapper = NOTE_ENTITY_TO_DATA_MAPPER
        )
    }

    @Provides
    @Singleton
    fun provideReminderDataRepository(reminderDao: ReminderDao): ReminderDataRepository {
        return ReminderDataRepositoryImpl(
            reminderDao = reminderDao,
            dataToEntityMapper = REMINDER_DATA_TO_ENTITY_MAPPER,
            entityToDataMapper = REMINDER_ENTITY_TO_DATA_MAPPER
        )
    }
}