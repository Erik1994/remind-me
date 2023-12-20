package com.compose.project.remindme.presentation.common

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.project.remindme.presentation.dialog.permission.PermissionsEnum
import com.compose.project.remindme.presentation.event.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel: ViewModel() {
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    val visiblePermissionDialogQueue = mutableStateListOf<PermissionsEnum>()


    fun sendUiEvent(uiEvent: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(uiEvent)
        }
    }

    fun dismissPermissionDialog() {
        visiblePermissionDialogQueue.removeFirst()
    }

    fun onPermissionResult(
        permission: PermissionsEnum,
        isGranted: Boolean
    ) {
        if (!isGranted && !visiblePermissionDialogQueue.contains(permission)) {
            visiblePermissionDialogQueue.add(permission)
        }
    }
}