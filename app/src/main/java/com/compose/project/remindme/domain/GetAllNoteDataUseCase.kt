package com.compose.project.remindme.domain

import com.compose.project.remindme.domain.model.NoteData
import kotlinx.coroutines.flow.Flow

interface GetAllNoteDataUseCase {
    operator fun invoke(): Flow<List<NoteData>>
}