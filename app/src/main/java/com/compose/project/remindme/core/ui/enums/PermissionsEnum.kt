package com.compose.project.remindme.core.ui.enums

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi
import com.compose.project.remindme.core.util.orDefault

enum class PermissionsEnum(val permission: String) {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    NOTIFICATION(Manifest.permission.POST_NOTIFICATIONS),
    @RequiresApi(Build.VERSION_CODES.S)
    EXACT_ALARM(Manifest.permission.SCHEDULE_EXACT_ALARM);

    companion object {
        fun getPermissionArray(): Array<String> {
            val permissionArray = values()
            return buildList {
                for (i in permissionArray.indices) {
                    if (permissionArray[i].permission == Manifest.permission.SCHEDULE_EXACT_ALARM &&
                        Build.VERSION.SDK_INT > Build.VERSION_CODES.TIRAMISU) {
                        continue
                    }
                    add(permissionArray[i].permission)
                }
            }.toTypedArray()
        }

        fun getPermissionByValue(value: String) = values().find { it.permission == value }.orDefault(NOTIFICATION)
    }
}