package com.compose.project.remindme.domain

import com.compose.project.remindme.domain.model.ArchivedData

interface InsertArchivedDataUseCase {
    suspend operator fun invoke(data: ArchivedData)
}