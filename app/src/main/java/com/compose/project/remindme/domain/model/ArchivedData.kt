package com.compose.project.remindme.domain.model

import java.time.LocalDateTime

data class ArchivedData(
    val id: Int = 0,
    val title: String,
    val description: String,
    val localDate: LocalDateTime,
    val isCompleted: Boolean = false
)
