package com.compose.project.remindme.presentation.component.color

import androidx.compose.ui.graphics.Color
import java.util.UUID

data class ColorItemData(
    val id: String = UUID.randomUUID().toString().replace("-", "").take(7),
    val color: Color,
    val isSelected: Boolean = false
)

val colorItemList: List<ColorItemData> = buildList {
    add(ColorItemData(color = Color.Gray, isSelected = true))
    add(ColorItemData(color = Color.LightGray))
    add(ColorItemData(color = Color.DarkGray))
    add(ColorItemData(color = Color.Green))
    add(ColorItemData(color = Color.Red))
    add(ColorItemData(color = Color.Yellow))
    add(ColorItemData(color = Color.Blue))
    add(ColorItemData(color = Color.White))
    add(ColorItemData(color = Color.Black))
}