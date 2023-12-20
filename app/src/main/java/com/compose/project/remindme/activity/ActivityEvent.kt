package com.compose.project.remindme.activity

import com.compose.project.remindme.presentation.dialog.permission.PermissionsEnum
import com.compose.project.remindme.presentation.navigation.BottomNavigationItem

sealed class ActivityEvent {
    object DismissPermissionDialogEvent : ActivityEvent()

    data class BottomNavigationItemClickEvent(val bottomNavigationItem: BottomNavigationItem) :
        ActivityEvent()

    data class OnPermissionResultEvent(val permission: PermissionsEnum, val isGranted: Boolean) :
        ActivityEvent()
}
