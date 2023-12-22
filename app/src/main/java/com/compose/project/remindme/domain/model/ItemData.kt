package com.compose.project.remindme.domain.model

import androidx.compose.ui.graphics.Color
import java.time.LocalDateTime

interface ItemData {
    val id: Int?
    val color: Color
    val title: String
    val description: String
    val localDate: LocalDateTime
    val isCompleted: Boolean
        get() = false
}