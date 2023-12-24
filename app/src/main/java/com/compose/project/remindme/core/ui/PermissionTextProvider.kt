package com.compose.project.remindme.core.ui

import com.compose.project.remindme.R
import com.compose.project.remindme.core.ui.enums.PermissionsEnum

sealed class PermissionTextProvider(val title: UiText = UiText.StringResource(R.string.permission_required)) {
    abstract fun getDescription(isPermanentlyDeclined: Boolean): UiText

    class NotificationPermissionTextProvider :
        PermissionTextProvider() {
        override fun getDescription(isPermanentlyDeclined: Boolean): UiText {
            return if (isPermanentlyDeclined) {
                UiText.StringResource(R.string.permanently_declined_notification_permission_message)
            } else {
                UiText.StringResource(R.string.permanently_not_declined_notification_permission_message)
            }
        }
    }

    class ExactAlarmPermissionTextProvider :
        PermissionTextProvider() {
        override fun getDescription(isPermanentlyDeclined: Boolean): UiText {
            return if (isPermanentlyDeclined) {
                UiText.StringResource(R.string.permanently_declined_exact_alarm_permission_message)
            } else {
                UiText.StringResource(R.string.permanently_not_declined_exact_alarm_permission_message)
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
                PermissionsEnum.EXACT_ALARM -> ExactAlarmPermissionTextProvider()
            }
        }
    }
}
