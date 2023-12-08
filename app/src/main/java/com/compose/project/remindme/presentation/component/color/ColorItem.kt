package com.compose.project.remindme.presentation.component.color

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.project.remindme.presentation.extension.isColorLight
import com.compose.project.remindme.ui.LocalDimension
import com.compose.project.remindme.ui.theme.RemindMeTheme


@Composable
fun ColorItem(
    modifier: Modifier = Modifier,
    colorItemData: ColorItemData,
    onItemClick: (Color) -> Unit
) {
    val dimension = LocalDimension.current
    Box(
        modifier = modifier
            .size(55.dp)
            .clip(MaterialTheme.shapes.small)
            .background(
                color = colorItemData.color,
                shape = MaterialTheme.shapes.small
            ),
        contentAlignment = Alignment.Center
    ) {
        if (colorItemData.isSelected) {
            Icon(
                imageVector = Icons.Filled.Done,
                contentDescription = "",
                tint = if (colorItemData.color.isColorLight()) {
                    Color.Black
                } else Color.White
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun RoundedIconSquarePreview() {
    RemindMeTheme {
        ColorItem(
            colorItemData = ColorItemData(color = Color.Black, isSelected = true),

            ) {

        }
    }
}