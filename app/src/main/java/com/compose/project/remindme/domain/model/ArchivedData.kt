package com.compose.project.remindme.domain.model

import java.time.LocalDate

data class ArchivedData(
    val id: Int = 0,
    val title: String,
    val description: String,
    val localDate: LocalDate,
    val isCompleted: Boolean = false
)
