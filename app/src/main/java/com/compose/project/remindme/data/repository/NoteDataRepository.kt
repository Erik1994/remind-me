package com.compose.project.remindme.data.repository

import com.compose.project.remindme.domain.model.NoteData
import kotlinx.coroutines.flow.Flow

interface NoteDataRepository {

    fun getAllNoteEntities(): Flow<List<NoteData>>

    suspend fun insertNoteEntity(data: NoteData)

    suspend fun deleteNoteEntity(data: NoteData)
}