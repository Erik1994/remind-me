package com.compose.project.remindme.domain

import com.compose.project.remindme.domain.model.Category
import com.compose.project.remindme.domain.model.ReminderData

interface ReminderDataToCategorizedUseCase {
    operator fun invoke(reminderDataList: List<ReminderData>): List<Category>
}