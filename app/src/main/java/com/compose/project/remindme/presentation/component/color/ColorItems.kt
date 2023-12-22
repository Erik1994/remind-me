package com.compose.project.remindme.presentation.component.color

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.compose.project.remindme.ui.theme.colors
import java.util.UUID

data class ColorItemData(
    val id: String = UUID.randomUUID().toString().replace("-", "").take(7),
    val color: Color = Color.White,
    val isSelected: Boolean = false
)

@Composable
fun ColorItemList(): List<ColorItemData> {
    return colors.mapIndexed { index, color ->
        ColorItemData(
            color = color,
            isSelected = index == 0
        )
    }
}