package com.compose.project.remindme.domain

import com.compose.project.remindme.domain.model.NoteData

interface InsertNoteDataUseCase {
    suspend operator fun invoke(data: NoteData)
}