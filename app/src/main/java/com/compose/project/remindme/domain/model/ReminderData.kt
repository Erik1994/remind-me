package com.compose.project.remindme.domain.model

import androidx.compose.ui.graphics.Color
import java.time.LocalDateTime

data class ReminderData (
    val id: Int? = null,
    val color: Color,
    val title: String,
    val description: String,
    val localDate: LocalDateTime,
    val isCompleted: Boolean = false
)