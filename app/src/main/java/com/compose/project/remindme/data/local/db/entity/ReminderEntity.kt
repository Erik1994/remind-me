package com.compose.project.remindme.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.compose.project.remindme.data.local.constant.LocalDataConstants

@Entity(tableName = LocalDataConstants.REMINDER_TABLE)
data class ReminderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val description: String,
    val year: Int,
    val month: Int,
    val dayOfMonth: Int,
    val hour: Int,
    val minute: Int,
    val color: Int,
    val isLocked: Boolean = false,
    val isCompleted: Boolean = false
)
