package com.compose.project.remindme.domain

import com.compose.project.remindme.domain.model.Category
import com.compose.project.remindme.domain.model.NoteData
import javax.inject.Inject

class NoteDataToCategorizedUseCaseImpl @Inject constructor() : NoteDataToCategorizedUseCase {
    override fun invoke(noteDataList: List<NoteData>): List<Category> {
        return if (noteDataList.isNotEmpty()) {
            listOf(
                Category(items = noteDataList)
            )
        } else {
            emptyList()
        }
    }
}