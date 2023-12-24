package com.compose.project.remindme.domain.model

import com.compose.project.remindme.core.ui.UiText
import com.compose.project.remindme.domain.model.ItemData

data class Category(
    val name: UiText = UiText.DynamicString(""),
    val items: List<ItemData> = emptyList()
)
