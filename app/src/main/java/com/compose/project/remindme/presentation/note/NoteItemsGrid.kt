package com.compose.project.remindme.presentation.note

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.compose.project.remindme.core.util.orDefault
import com.compose.project.remindme.domain.model.NoteData
import com.compose.project.remindme.ui.LocalDimension

@Composable
fun NoteItemsGrid(
    modifier: Modifier = Modifier,
    notes: List<NoteData>,
    onItemClick: (NoteData) -> Unit,
    onDeleteClick: (NoteData) -> Unit
) {
    val dimensions = LocalDimension.current
    LazyVerticalStaggeredGrid(
        modifier = Modifier.fillMaxSize(),
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
            NoteItem(
                noteData = item,
                onItemClick = onItemClick,
                onDeleteClick = onDeleteClick
            )
        }
    }
}