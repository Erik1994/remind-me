package com.compose.project.remindme.presentation.component.item

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.compose.project.remindme.core.util.orDefault
import com.compose.project.remindme.domain.model.Category
import com.compose.project.remindme.domain.model.ItemData
import com.compose.project.remindme.ui.LocalDimension

@Composable
fun ItemsGrid(
    modifier: Modifier = Modifier,
    categorizedItems: List<Category>,
    onItemClick: (ItemData) -> Unit,
    onDeleteClick: (ItemData) -> Unit,
    onLockClick: (ItemData) -> Unit
) {
    val dimensions = LocalDimension.current
    val context = LocalContext.current

    LazyVerticalStaggeredGrid(
        modifier = modifier.fillMaxSize(),
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(dimensions.spaceMedium),
        verticalItemSpacing = dimensions.spaceMedium,
        horizontalArrangement = Arrangement.spacedBy(dimensions.spaceMedium)
    ) {
        categorizedItems.forEach { category ->
            val header = category.name.asString(context)
            if (header.isNotBlank()) {
                header {
                    CategoryHeader(category = header)
                }
            }
            items(
                items = category.items,
                key = { item ->
                    item.id.orDefault(0)
                }
            ) { item ->
                val isDeleted = remember { mutableStateOf(false) }
                AnimatedVisibility(
                    visible = !isDeleted.value,
                    enter = expandVertically(),
                    exit = shrinkVertically(animationSpec = tween(durationMillis = 1000))
                ) {
                    Item(
                        itemData = item,
                        onItemClick = onItemClick,
                        onDeleteClick = {
                            isDeleted.value = true
                            onDeleteClick(item)
                        },
                        onLockClick = onLockClick
                    )
                }
            }
        }
    }
}

fun LazyStaggeredGridScope.header(
    content: @Composable LazyStaggeredGridItemScope.() -> Unit
) {
    item(span = StaggeredGridItemSpan.FullLine, content = content)
}