package com.compose.project.remindme.domain

import com.compose.project.remindme.data.repository.NoteDataRepository
import com.compose.project.remindme.domain.model.NoteData
import javax.inject.Inject

class InsertNoteDataUseCaseImpl @Inject constructor(
    private val noteDataRepository: NoteDataRepository
): InsertNoteDataUseCase {
    override suspend fun invoke(data: NoteData) {
        noteDataRepository.insertNoteEntity(data = data)
    }
}