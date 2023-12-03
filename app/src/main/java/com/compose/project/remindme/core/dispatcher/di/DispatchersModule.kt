package com.compose.project.remindme.core.dispatcher.di

import com.compose.project.remindme.core.constant.NamedDispatchers
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
    fun provideAppDispatchers(): AppDispatchers {
        return AppDispatcherImpl
    }

    @Provides
    @Singleton
    @Named(NamedDispatchers.DISPATCHER_IO)
    fun provideIoDispatcher(appDispatchers: AppDispatchers): CoroutineDispatcher {
        return appDispatchers.ioDispatcher
    }

    @Provides
    @Singleton
    @Named(NamedDispatchers.DISPATCHER_UI)
    fun provideDispatcherUI(appDispatchers: AppDispatchers): CoroutineDispatcher {
        return appDispatchers.mainDispatcher
    }

    @Provides
    @Singleton
    @Named(NamedDispatchers.DISPATCHER_DEFAULT)
    fun provideDispatcherDefault(appDispatchers: AppDispatchers): CoroutineDispatcher {
        return appDispatchers.defaultDispatcher
    }
}