package com.compose.project.remindme.data.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.compose.project.remindme.data.local.constant.LocalDataConstants
import com.compose.project.remindme.data.local.db.entity.ArchivedEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArchivedDao {

    @Query("SELECT * FROM ${LocalDataConstants.ARCHIVED_TABLE}")
    fun getAllArchivedEntities(): Flow<List<ArchivedEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArchivedEntity(entity: ArchivedEntity)

    @Delete
    suspend fun deleteArchivedEntity(entity: ArchivedEntity)
}
