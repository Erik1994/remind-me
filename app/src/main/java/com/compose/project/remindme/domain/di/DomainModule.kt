package com.compose.project.remindme.domain.di

import com.compose.project.remindme.domain.DeleteArchivedDataUseCase
import com.compose.project.remindme.domain.DeleteArchivedDataUseCaseImpl
import com.compose.project.remindme.domain.DeleteNoteDataUseCase
import com.compose.project.remindme.domain.DeleteNoteDataUseCaseImpl
import com.compose.project.remindme.domain.DeleteReminderDataUseCase
import com.compose.project.remindme.domain.DeleteReminderDataUseCaseImpl
import com.compose.project.remindme.domain.GetAllArchivedDataUseCase
import com.compose.project.remindme.domain.GetAllArchivedDataUseCaseImpl
import com.compose.project.remindme.domain.GetAllNoteDataUseCase
import com.compose.project.remindme.domain.GetAllNoteDataUseCaseImpl
import com.compose.project.remindme.domain.GetAllReminderDataUseCase
import com.compose.project.remindme.domain.GetAllReminderDataUseCaseImpl
import com.compose.project.remindme.domain.InsertArchivedDataUseCase
import com.compose.project.remindme.domain.InsertArchivedDataUseCaseImpl
import com.compose.project.remindme.domain.InsertNoteDataUseCase
import com.compose.project.remindme.domain.InsertNoteDataUseCaseImpl
import com.compose.project.remindme.domain.InsertReminderDataUseCase
import com.compose.project.remindme.domain.InsertReminderDataUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class DomainModule {

    @Binds
    @ViewModelScoped
    abstract fun provideDeleteNoteDataUseCase(deleteNoteDataUseCaseImpl: DeleteNoteDataUseCaseImpl): DeleteNoteDataUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideInsertNoteDataUseCase(insertNoteDataUseCaseImpl: InsertNoteDataUseCaseImpl): InsertNoteDataUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideGetAllNoteDataUseCase(getAllNoteDataUseCaseImpl: GetAllNoteDataUseCaseImpl): GetAllNoteDataUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideDeleteArchivedUseCase(deleteArchivedDataUseCaseImpl: DeleteArchivedDataUseCaseImpl): DeleteArchivedDataUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideInsertArchivedUseCase(insertArchivedDataUseCaseImpl: InsertArchivedDataUseCaseImpl): InsertArchivedDataUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideGetAllArchivedUseCase(getAllArchivedDataUseCaseImpl: GetAllArchivedDataUseCaseImpl): GetAllArchivedDataUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideDeleteReminderDataUseCase(deleteReminderDataUseCaseImpl: DeleteReminderDataUseCaseImpl): DeleteReminderDataUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideInsertReminderDataUseCase(insertReminderDataUseCaseImpl: InsertReminderDataUseCaseImpl): InsertReminderDataUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideGetAllReminderDataUseCase(getAllReminderDataUseCaseImpl: GetAllReminderDataUseCaseImpl): GetAllReminderDataUseCase

}