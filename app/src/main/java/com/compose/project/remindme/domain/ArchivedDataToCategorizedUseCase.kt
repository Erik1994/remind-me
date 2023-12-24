package com.compose.project.remindme.domain

import com.compose.project.remindme.domain.model.ArchivedData
import com.compose.project.remindme.domain.model.Category
import com.compose.project.remindme.domain.model.NoteData

interface ArchivedDataToCategorizedUseCase {
    operator fun invoke(archivedDataList: List<ArchivedData>): List<Category>
}