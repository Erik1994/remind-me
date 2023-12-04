package com.compose.project.remindme.domain

import com.compose.project.remindme.data.repository.NoteDataRepository
import com.compose.project.remindme.domain.model.NoteData
import javax.inject.Inject

class DeleteNoteDataUseCaseImpl @Inject constructor(
    private val noteDataRepository: NoteDataRepository
): DeleteNoteDataUseCase {
    override suspend fun invoke(data: NoteData) {
        noteDataRepository.deleteNoteEntity(data = data)
    }
}