package com.compose.project.remindme.core.ui.enums

import android.Manifest

enum class PermissionsEnum(val permission: String) {
    NOTIFICATION(Manifest.permission.POST_NOTIFICATIONS)
}