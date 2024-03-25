package com.compose.project.remindme.activity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.compose.project.remindme.presentation.common.BaseViewModel
import com.compose.project.remindme.presentation.event.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor() : BaseViewModel() {
    var activityState by mutableStateOf(ActivityState())
        private set

    fun sendEvent(activityEvent: ActivityEvent) {
        when (activityEvent) {
            is ActivityEvent.BottomNavigationItemClickEvent -> {
                activityState = activityState.copy(
                    bottomNavigationItemList = activityState.bottomNavigationItemList.map {
                        it.copy(isSelected = it.route == activityEvent.bottomNavigationItem.route)
                    }
                )
                sendUiEvent(UiEvent.Navigate(activityEvent.bottomNavigationItem.route))
            }

            is ActivityEvent.OnPermissionResultEvent -> {
                activityState = activityState.copy(needRequestPermission = false)
                onPermissionResult(
                    permission = activityEvent.permission,
                    isGranted = activityEvent.isGranted
                )
            }

            is ActivityEvent.DismissPermissionDialogEvent -> {
                activityState = activityState.copy(needRequestPermission = true)
                dismissPermissionDialog()
            }

            is ActivityEvent.ReminderNotificationClickEvent -> {
                activityState = activityState.copy(
                    defaultRoute = activityEvent.route,
                    bottomNavigationItemList = activityState.bottomNavigationItemList.map {
                        it.copy(
                            isSelected = it.route == activityEvent.route
                        )
                    }
                )
            }

            is ActivityEvent.ShowBiometricResultMessageEvent -> {
                sendUiEvent(UiEvent.ShowSnackBar(activityEvent.biometricResult.resulMessage))
            }
        }
    }
}