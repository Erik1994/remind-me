package com.compose.project.remindme.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.compose.project.remindme.data.local.constant.LocalDataConstants

@Entity(tableName = LocalDataConstants.NOTE_TABLE)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val createdTimeInMillis: Long,
    val isCompleted: Boolean = false
)