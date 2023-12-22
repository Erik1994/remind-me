package com.compose.project.remindme.presentation.component.item

import com.compose.project.remindme.domain.model.ItemData

data class ItemState(
    val items: List<ItemData> = emptyList()
)
