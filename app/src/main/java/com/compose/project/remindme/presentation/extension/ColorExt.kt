package com.compose.project.remindme.presentation.extension

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils

fun Color.isColorLight(threshold: Float = 0.5f): Boolean {
    val luminance = ColorUtils.calculateLuminance(this.toArgb())
    return luminance > threshold
}