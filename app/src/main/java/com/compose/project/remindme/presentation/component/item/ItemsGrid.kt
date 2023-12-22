package com.compose.project.remindme.presentation.component.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.compose.project.remindme.core.util.orDefault
import com.compose.project.remindme.domain.model.ItemData
import com.compose.project.remindme.ui.LocalDimension

@Composable
fun ItemsGrid(
    modifier: Modifier = Modifier,
    notes: List<ItemData>,
    onItemClick: (ItemData) -> Unit,
    onDeleteClick: (ItemData) -> Unit
) {
    val dimensions = LocalDimension.current
    LazyVerticalStaggeredGrid(
        modifier = modifier.fillMaxSize(),
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(dimensions.spaceMedium),
        verticalItemSpacing = dimensions.spaceMedium,
        horizontalArrangement = Arrangement.spacedBy(dimensions.spaceMedium)
    ) {
        items(
            items = notes,
            key = { item ->
                item.id.orDefault(0)
            }
        ) { item ->
            Item(
                itemData = item,
                onItemClick = onItemClick,
                onDeleteClick = onDeleteClick
            )
        }
    }
}