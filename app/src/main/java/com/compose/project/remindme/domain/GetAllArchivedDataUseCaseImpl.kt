package com.compose.project.remindme.domain

import com.compose.project.remindme.data.repository.ArchivedDataRepository
import com.compose.project.remindme.domain.model.ArchivedData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllArchivedDataUseCaseImpl @Inject constructor(
    private val archivedDataRepository: ArchivedDataRepository
) : GetAllArchivedDataUseCase {
    override fun invoke(): Flow<List<ArchivedData>> {
        return archivedDataRepository.getAllArchivedEntities()
    }
}