package com.compose.project.remindme.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.compose.project.remindme.ui.LocalDimension

@Composable
fun AddButton(
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    icon: ImageVector = Icons.Filled.Add,
    onClick: () -> Unit
) {
    val dimension = LocalDimension.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimension.spaceMedium),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        FloatingActionButton(
            modifier = Modifier.clip(MaterialTheme.shapes.large),
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
}