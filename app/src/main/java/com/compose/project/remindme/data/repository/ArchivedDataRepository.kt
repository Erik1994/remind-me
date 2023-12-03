package com.compose.project.remindme.data.repository

import com.compose.project.remindme.domain.model.ArchivedData
import kotlinx.coroutines.flow.Flow

interface ArchivedDataRepository {

    fun getAllArchivedEntities(): Flow<List<ArchivedData>>

    suspend fun insertArchivedEntity(data: ArchivedData)

    suspend fun deleteArchivedEntity(data: ArchivedData)
}