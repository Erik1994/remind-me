package com.compose.project.remindme.data.repository

import com.compose.project.remindme.core.util.Mapper
import com.compose.project.remindme.data.local.db.dao.ReminderDao
import com.compose.project.remindme.data.local.db.entity.ReminderEntity
import com.compose.project.remindme.domain.model.ReminderData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ReminderDataRepositoryImpl (
    private val reminderDao: ReminderDao,
    private val dataToEntityMapper: Mapper<ReminderData, ReminderEntity>,
    private val entityToDataMapper: Mapper<ReminderEntity, ReminderData>
) : ReminderDataRepository {

    override fun getAllReminderEntities(): Flow<List<ReminderData>> {
        return reminderDao.getAllReminderEntities().map { reminderEntities ->
            reminderEntities.map { entityToDataMapper.map(it) }
        }
    }

    override suspend fun insertReminderEntity(data: ReminderData) {
        return reminderDao.insertReminderEntity(dataToEntityMapper.map(data))
    }

    override suspend fun deleteReminderEntity(data: ReminderData) {
        return reminderDao.deleteReminderEntity(dataToEntityMapper.map(data))
    }
}