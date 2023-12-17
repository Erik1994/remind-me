package com.compose.project.remindme.domain

import com.compose.project.remindme.data.repository.NoteDataRepository
import com.compose.project.remindme.domain.model.NoteData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllNoteDataUseCaseImpl @Inject constructor(
    private val noteDataRepository: NoteDataRepository
): GetAllNoteDataUseCase {
    override fun invoke(): Flow<List<NoteData>> {
        return noteDataRepository.getAllNoteEntities()
    }
}