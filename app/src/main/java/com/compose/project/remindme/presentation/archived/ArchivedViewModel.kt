package com.compose.project.remindme.presentation.archived

import com.compose.project.remindme.domain.model.ItemData
import com.compose.project.remindme.presentation.common.ScreenBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArchivedViewModel @Inject constructor() : ScreenBaseViewModel() {
    override fun handleItemClickEvent(itemData: ItemData) {

    }

    override fun handleItemDeleteClickEvent(itemData: ItemData) {

    }
}