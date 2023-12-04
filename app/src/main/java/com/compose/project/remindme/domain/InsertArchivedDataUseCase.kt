package com.compose.project.remindme.domain

import com.compose.project.remindme.domain.model.ArchivedData

interface InsertArchivedDataUseCase {
    suspend fun invoke(data: ArchivedData)
}