package com.compose.project.remindme.presentation.component.item

import com.compose.project.remindme.domain.model.ItemData

sealed class ItemEvent {
    data class ItemDeleteClickEvent(val itemData: ItemData) : ItemEvent()
    data class ItemClickEvent(val itemData: ItemData) : ItemEvent()
}