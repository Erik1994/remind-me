package com.compose.project.remindme.presentation.dialog.item

import androidx.compose.ui.graphics.Color
import java.time.LocalDateTime

data class DialogItemData(
    val id: Int? = null,
    val color: Color,
    val itemTitle: String,
    val dateTime: LocalDateTime = LocalDateTime.now(),
    val itemDescription: String = ""
)
