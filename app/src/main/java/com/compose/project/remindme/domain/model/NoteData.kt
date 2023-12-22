package com.compose.project.remindme.domain.model

import androidx.compose.ui.graphics.Color
import java.time.LocalDateTime

data class NoteData(
    override val id: Int? = null,
    override val color: Color,
    override val title: String,
    override val description: String,
    override val localDate: LocalDateTime
) : ItemData