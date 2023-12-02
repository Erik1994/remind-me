package com.compose.project.remindme.core.dispatcher.di

import com.compose.project.remindme.core.dispatcher.AppDispatcherImpl
import com.compose.project.remindme.core.dispatcher.AppDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

    @Provides
    @Singleton
    fun provideAppDispatchers(): AppDispatchers = AppDispatcherImpl

    @Provides
    @Singleton
    @Named(DISPATCHER_IO)
    fun provideIoDispatcher(appDispatchers: AppDispatchers): CoroutineDispatcher =
        appDispatchers.ioDispatcher

    @Provides
    @Singleton
    @Named(DISPATCHER_UI)
    fun provideDispatcherUI(appDispatchers: AppDispatchers): CoroutineDispatcher =
        appDispatchers.mainDispatcher

    @Provides
    @Singleton
    @Named(DISPATCHER_DEFAULT)
    fun provideDispatcherDefault(appDispatchers: AppDispatchers): CoroutineDispatcher =
        appDispatchers.defaultDispatcher

    const val DISPATCHER_IO = "DISPATCHER_IO"
    const val DISPATCHER_UI = "DISPATCHER_UI"
    const val DISPATCHER_DEFAULT = "DISPATCHER_DEFAULT"
}