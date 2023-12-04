package com.compose.project.remindme.domain

import com.compose.project.remindme.domain.model.ArchivedData

interface DeleteArchivedDataUseCase {
    suspend operator fun invoke(data: ArchivedData)
}