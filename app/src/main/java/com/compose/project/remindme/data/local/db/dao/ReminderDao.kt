package com.compose.project.remindme.data.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.compose.project.remindme.data.local.constant.LocalDataConstants
import com.compose.project.remindme.data.local.db.entity.ReminderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {

    @Query("SELECT * FROM ${LocalDataConstants.REMINDER_TABLE} ORDER BY month ASC, dayOfMonth ASC, minute ASC")
    fun getAllOrderedReminderEntities(): Flow<List<ReminderEntity>>

    @Query("SELECT * FROM ${LocalDataConstants.REMINDER_TABLE}")
    suspend fun getAllReminderEntities(): List<ReminderEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminderEntity(entity: ReminderEntity)

    @Query("SELECT * FROM ${LocalDataConstants.REMINDER_TABLE} WHERE id = :id")
    suspend fun getReminderEntityById(id: Int): ReminderEntity?

    @Delete
    suspend fun deleteReminderEntity(entity: ReminderEntity)
}