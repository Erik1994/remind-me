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
    modifier: Modifier = Modifier
) {
    val dimension = LocalDimension.current
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = dimension.spaceSmall,
                end = dimension.spaceSmall,
                top = dimension.spaceSmall,
                bottom = dimension.spaceSmall
            ),
        horizontalArrangement = Arrangement.spacedBy(dimension.spaceSmall)

    ) {
        items(
            items = colorItems,
            key = { item -> item.id }
        ) { item ->

            ColorItem(colorItemData = item){

            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ColorItemsHorizontalListPreview() {
    RemindMeTheme {
        ColorItemsHorizontalList()
    }
}