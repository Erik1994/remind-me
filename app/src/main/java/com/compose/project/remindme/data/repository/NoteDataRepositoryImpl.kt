package com.compose.project.remindme.data.repository

import com.compose.project.remindme.core.util.Mapper
import com.compose.project.remindme.data.local.db.dao.NoteDao
import com.compose.project.remindme.data.local.db.entity.NoteEntity
import com.compose.project.remindme.domain.model.NoteData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class NoteDataRepositoryImpl(
    private val noteDao: NoteDao,
    private val ioDispatcher: CoroutineDispatcher,
    private val dataToEntityMapper: Mapper<NoteData, NoteEntity>,
    private val entityToDataMapper: Mapper<NoteEntity, NoteData>
) : NoteDataRepository {

    override fun getAllNoteEntities(): Flow<List<NoteData>> {
        return noteDao.getAllNoteEntities().map { noteEntities ->
            noteEntities.map { entityToDataMapper.map(it) }
        }
    }

    override suspend fun insertNoteEntity(data: NoteData) {
        withContext(ioDispatcher) {
            noteDao.insertNoteEntity(dataToEntityMapper.map(data))
        }
    }

    override suspend fun deleteNoteEntity(data: NoteData) {
        withContext(ioDispatcher) {
            noteDao.deleteNoteEntity(dataToEntityMapper.map(data))
        }
    }
}