package com.compose.project.remindme.domain

import com.compose.project.remindme.domain.model.Category
import com.compose.project.remindme.domain.model.NoteData

interface NoteDataToCategorizedUseCase {
    operator fun invoke(noteDataList: List<NoteData>): List<Category>
}