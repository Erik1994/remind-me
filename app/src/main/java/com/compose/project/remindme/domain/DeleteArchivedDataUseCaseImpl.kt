package com.compose.project.remindme.domain

import com.compose.project.remindme.data.repository.ArchivedDataRepository
import com.compose.project.remindme.domain.model.ArchivedData
import javax.inject.Inject

class DeleteArchivedDataUseCaseImpl @Inject constructor(
    private val archivedDataRepository: ArchivedDataRepository
) : DeleteArchivedDataUseCase {
    override suspend fun invoke(data: ArchivedData) {
        archivedDataRepository.deleteArchivedEntity(data = data)
    }
}