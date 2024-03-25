package com.compose.project.remindme.presentation.common

import com.compose.project.remindme.domain.model.ItemData
import com.compose.project.remindme.presentation.component.item.ItemEvent

abstract class ScreenBaseViewModel : BaseViewModel() {
    abstract fun handleItemClickEvent(itemData: ItemData)
    abstract fun handleItemDeleteClickEvent(itemData: ItemData)
    abstract fun toggleItemLockState()
    abstract fun resetLockUnlockItemId()
    fun sendItemEvent(itemEvent: ItemEvent) {
        when(itemEvent) {
            is ItemEvent.ItemClickEvent -> handleItemClickEvent(itemEvent.itemData)
            is ItemEvent.ItemDeleteClickEvent -> handleItemDeleteClickEvent(itemEvent.itemData)
        }
    }
}