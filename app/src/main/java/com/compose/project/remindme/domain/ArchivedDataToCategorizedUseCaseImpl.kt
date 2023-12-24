package com.compose.project.remindme.domain

import com.compose.project.remindme.domain.model.ArchivedData
import com.compose.project.remindme.domain.model.Category
import javax.inject.Inject

class ArchivedDataToCategorizedUseCaseImpl @Inject constructor() : ArchivedDataToCategorizedUseCase {
    override fun invoke(archivedDataList: List<ArchivedData>): List<Category> {
        return if (archivedDataList.isNotEmpty()) {
            listOf(
                Category(items = archivedDataList)
            )
        } else {
            emptyList()
        }
    }
}