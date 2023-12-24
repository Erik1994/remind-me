package com.compose.project.remindme.presentation.archived

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.compose.project.remindme.R
import com.compose.project.remindme.presentation.component.EmptyScreenPlaceHolder
import com.compose.project.remindme.presentation.component.ScreenHeader
import com.compose.project.remindme.presentation.component.item.ItemEvent
import com.compose.project.remindme.presentation.component.item.ItemsGrid
import com.compose.project.remindme.ui.LocalDimension

@Composable
fun ArchivedScreen(
    modifier: Modifier = Modifier,
    viewModel: ArchivedViewModel = hiltViewModel()
) {
    val archivedState = viewModel.archivedState
    val dimensions = LocalDimension.current
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ScreenHeader(title = stringResource(id = R.string.archived))
        if (archivedState.categorizedItems.isEmpty()) {
            EmptyScreenPlaceHolder(message = R.string.empty_completed_message)
        } else {
            ItemsGrid(
                categorizedItems = archivedState.categorizedItems,
                onItemClick = {
                    viewModel.sendItemEvent(ItemEvent.ItemClickEvent(it))
                },
                onDeleteClick = {
                    viewModel.sendItemEvent(ItemEvent.ItemDeleteClickEvent(it))
                }
            )
        }
    }
}