package com.compose.project.remindme.presentation.dialog.permission

import android.Manifest

enum class PermissionsEnum(val permission: String) {
    NOTIFICATION(Manifest.permission.POST_NOTIFICATIONS)
}