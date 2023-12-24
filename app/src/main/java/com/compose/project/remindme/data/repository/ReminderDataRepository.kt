package com.compose.project.remindme.data.repository

import com.compose.project.remindme.data.local.db.entity.ReminderEntity
import com.compose.project.remindme.domain.model.ReminderData
import kotlinx.coroutines.flow.Flow

interface ReminderDataRepository {

    fun getAllOrderedReminderEntities(): Flow<List<ReminderData>>

    suspend fun getAllReminderEntities(): List<ReminderData>

    suspend fun insertReminderEntity(data: ReminderData)

    suspend fun getReminderEntityById(id: Int): ReminderData?

    suspend fun deleteReminderEntity(data: ReminderData)
}