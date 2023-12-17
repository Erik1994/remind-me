package com.compose.project.remindme.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.compose.project.remindme.ui.LocalDimension

@Composable
fun ScreenHeader(
    modifier: Modifier = Modifier,
    title: String,
    style: TextStyle = MaterialTheme.typography.displayLarge
) {
    val dimensions = LocalDimension.current
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = dimensions.spaceMedium, top = dimensions.spaceLarge),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(text = title, style = style)
    }
}