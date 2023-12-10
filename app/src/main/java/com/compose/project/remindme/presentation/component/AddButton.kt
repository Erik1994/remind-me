package com.compose.project.remindme.presentation.component

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.compose.project.remindme.ui.LocalDimension

@Composable
fun AddButton(
    containerColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    icon: ImageVector = Icons.Filled.Add,
    onClick: () -> Unit
) {
    val dimension = LocalDimension.current

    FloatingActionButton(
        shape = CircleShape,
        onClick = onClick,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = dimension.spaceMedium,
            pressedElevation = dimension.spaceLarge
        ),
        containerColor = containerColor,
        contentColor = contentColor

    ) {
        Icon(imageVector = icon, contentDescription = "")
    }
}