package com.compose.project.remindme.presentation.component.color

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.compose.project.remindme.ui.LocalDimension
import com.compose.project.remindme.ui.theme.RemindMeTheme


@Composable
fun ColorItemsHorizontalList(
    modifier: Modifier = Modifier,
    colorItems: List<ColorItemData>,
    onColorItemClick: (Color) -> Unit
) {
    val dimension = LocalDimension.current
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(dimension.spaceMedium),
        horizontalArrangement = Arrangement.spacedBy(dimension.spaceSmall)
    ) {
        items(
            items = colorItems,
            key = { item -> item.id }
        ) { item ->
            ColorItem(colorItemData = item, onItemClick = onColorItemClick)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ColorItemsHorizontalListPreview() {
    RemindMeTheme {
        ColorItemsHorizontalList(colorItems = colorItemList, onColorItemClick = {})
    }
}