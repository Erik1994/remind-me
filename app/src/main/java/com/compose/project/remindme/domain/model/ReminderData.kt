package com.compose.project.remindme.domain.model

import java.time.LocalDateTime

data class ReminderData (
    val id: Int = 0,
    val title: String,
    val description: String,
    val localDate: LocalDateTime,
    val isCompleted: Boolean = false
)