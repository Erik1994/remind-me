package com.compose.project.remindme.core.ui.enums

import android.Manifest
import com.compose.project.remindme.core.util.orDefault

enum class PermissionsEnum(val permission: String) {
    NOTIFICATION(Manifest.permission.POST_NOTIFICATIONS),
    EXACT_ALARM(Manifest.permission.SCHEDULE_EXACT_ALARM);

    companion object {
        fun getPermissionArray() = values().map {
            it.permission
        }.toTypedArray()

        fun getPermissionByValue(value: String) = values().find { it.permission == value }.orDefault(NOTIFICATION)
    }
}