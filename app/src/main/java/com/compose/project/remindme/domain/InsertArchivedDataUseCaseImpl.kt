package com.compose.project.remindme.domain

import com.compose.project.remindme.data.repository.ArchivedDataRepository
import com.compose.project.remindme.domain.model.ArchivedData
import javax.inject.Inject

class InsertArchivedDataUseCaseImpl @Inject constructor(
    private val archivedDataRepository: ArchivedDataRepository
): InsertArchivedDataUseCase {
    override suspend fun invoke(data: ArchivedData) {
        archivedDataRepository.insertArchivedEntity(data = data)
    }
}