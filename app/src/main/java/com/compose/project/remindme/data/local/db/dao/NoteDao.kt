package com.compose.project.remindme.data.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.compose.project.remindme.data.local.constant.LocalDataConstants
import com.compose.project.remindme.data.local.db.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM ${LocalDataConstants.NOTE_TABLE}")
    suspend fun getAllNoteEntities(): Flow<List<NoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNoteEntity(entity: NoteEntity)

    @Delete
    suspend fun deleteNoteEntity(entity: NoteEntity)
}