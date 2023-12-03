package com.compose.project.remindme.data.repository

import com.compose.project.remindme.domain.model.ReminderData
import kotlinx.coroutines.flow.Flow

interface ReminderDataRepository {

    fun getAllReminderEntities(): Flow<List<ReminderData>>

    suspend fun insertReminderEntity(data: ReminderData)

    suspend fun deleteReminderEntity(data: ReminderData)
}