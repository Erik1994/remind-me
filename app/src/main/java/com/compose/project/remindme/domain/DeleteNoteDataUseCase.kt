package com.compose.project.remindme.domain

import com.compose.project.remindme.domain.model.NoteData

interface DeleteNoteDataUseCase {
    suspend operator fun invoke(data: NoteData)
}