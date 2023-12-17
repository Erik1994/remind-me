package com.compose.project.remindme.data.repository

import com.compose.project.remindme.core.util.Mapper
import com.compose.project.remindme.data.local.db.dao.ArchivedDao
import com.compose.project.remindme.data.local.db.entity.ArchivedEntity
import com.compose.project.remindme.domain.model.ArchivedData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class ArchivedDataRepositoryImpl(
    private val archivedDao: ArchivedDao,
    private val ioDispatcher: CoroutineDispatcher,
    private val dataToEntityMapper: Mapper<ArchivedData, ArchivedEntity>,
    private val entityToDataMapper: Mapper<ArchivedEntity, ArchivedData>
) : ArchivedDataRepository {

    override fun getAllArchivedEntities(): Flow<List<ArchivedData>> {
        return archivedDao.getAllArchivedEntities().map { archivedEntities ->
            archivedEntities.map { entityToDataMapper.map(it) }
        }
    }

    override suspend fun insertArchivedEntity(data: ArchivedData) {
        withContext(ioDispatcher) {
            archivedDao.insertArchivedEntity(dataToEntityMapper.map(data))
        }
    }

    override suspend fun deleteArchivedEntity(data: ArchivedData) {
        withContext(ioDispatcher) {
            archivedDao.deleteArchivedEntity(dataToEntityMapper.map(data))
        }
    }
}