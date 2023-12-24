package com.compose.project.remindme.domain

import com.compose.project.remindme.domain.model.ArchivedData
import kotlinx.coroutines.flow.Flow

interface GetAllArchivedDataUseCase {
    operator fun invoke(): Flow<List<ArchivedData>>
}