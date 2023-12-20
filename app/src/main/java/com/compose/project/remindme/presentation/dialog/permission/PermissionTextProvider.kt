package com.compose.project.remindme.presentation.dialog.permission

import com.compose.project.remindme.R
import com.compose.project.remindme.core.util.UiText

sealed class PermissionTextProvider(val title: UiText) {
    abstract fun getDescription(isPermanentlyDeclined: Boolean): UiText

    class NotificationPermissionTextProvider :
        PermissionTextProvider(UiText.StringResource(R.string.permission_required)) {
        override fun getDescription(isPermanentlyDeclined: Boolean): UiText {
            return if (isPermanentlyDeclined) {
                UiText.StringResource(R.string.permanently_declined_permission_message)
            } else {
                UiText.StringResource(R.string.permanently_not_declined_notification_permission_message)
            }
        }
    }

    private interface PermissionTextProviderI {
        fun getPermissionTextProvider(permissionsEnum: PermissionsEnum): PermissionTextProvider
    }

    companion object : PermissionTextProviderI {
        override fun getPermissionTextProvider(permissionsEnum: PermissionsEnum): PermissionTextProvider {
            return when (permissionsEnum) {
                PermissionsEnum.NOTIFICATION -> NotificationPermissionTextProvider()
            }
        }
    }
}
