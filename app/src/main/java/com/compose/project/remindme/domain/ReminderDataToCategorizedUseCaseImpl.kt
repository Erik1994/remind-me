package com.compose.project.remindme.domain

import com.compose.project.remindme.R
import com.compose.project.remindme.core.ui.UiText
import com.compose.project.remindme.domain.model.Category
import com.compose.project.remindme.domain.model.ReminderData
import java.time.LocalDateTime
import javax.inject.Inject

class ReminderDataToCategorizedUseCaseImpl @Inject constructor() :
    ReminderDataToCategorizedUseCase {
    override fun invoke(reminderDataList: List<ReminderData>): List<Category> {
        return if (reminderDataList.isNotEmpty()) {
            val groupedMap = reminderDataList.groupBy {
                if (it.localDate.dayOfMonth > LocalDateTime.now().dayOfMonth) {
                    R.string.upcoming
                } else R.string.pinned
            }
            buildList {
                groupedMap.forEach { (resourceId, items) ->
                    add(
                        Category(
                            name = UiText.StringResource(resourceId),
                            items = items
                        )
                    )
                }
            }
        } else {
            emptyList()
        }
    }
}