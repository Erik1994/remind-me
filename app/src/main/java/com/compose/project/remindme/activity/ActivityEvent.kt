package com.compose.project.remindme.activity

import com.compose.project.remindme.core.ui.enums.PermissionsEnum
import com.compose.project.remindme.presentation.biometric.BiometricManager
import com.compose.project.remindme.presentation.navigation.BottomNavigationItem

sealed class ActivityEvent {
    object DismissPermissionDialogEvent : ActivityEvent()

    data class BottomNavigationItemClickEvent(val bottomNavigationItem: BottomNavigationItem) :
        ActivityEvent()

    data class OnPermissionResultEvent(val permission: PermissionsEnum, val isGranted: Boolean) :
        ActivityEvent()

    data class ReminderNotificationClickEvent(val route: String) : ActivityEvent()

    data class ShowBiometricResultMessageEvent(val biometricResult: BiometricManager.BiometricResult): ActivityEvent()
}
