package com.compose.project.remindme.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.compose.project.remindme.data.local.db.dao.ArchivedDao
import com.compose.project.remindme.data.local.db.dao.NoteDao
import com.compose.project.remindme.data.local.db.dao.ReminderDao
import com.compose.project.remindme.data.local.db.entity.ArchivedEntity
import com.compose.project.remindme.data.local.db.entity.NoteEntity
import com.compose.project.remindme.data.local.db.entity.ReminderEntity

@Database(
    entities = [NoteEntity::class, ReminderEntity::class, ArchivedEntity::class],
    version = 3
)
abstract class AppDb : RoomDatabase() {
    abstract val noteDao: NoteDao
    abstract val reminderDao: ReminderDao
    abstract val archivedDao: ArchivedDao
}