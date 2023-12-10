package com.compose.project.remindme.presentation.dialog.itemdialog

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.compose.project.remindme.presentation.common.BaseViewModel
import com.compose.project.remindme.presentation.event.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ItemDialogViewModel @Inject constructor() : BaseViewModel() {
    var itemDialogState: ItemDialogState by mutableStateOf(ItemDialogState())
        private set

    fun setDefaultStateIfNeeded(dialogItemData: DialogItemData?, dialogType: ItemDialogType) {
        if (itemDialogState.needSetDefaultValue) {
            itemDialogState = dialogItemData?.let { dialogItemData ->
                itemDialogState.copy(
                    colorItems = itemDialogState.colorItems.map {
                        it.copy(
                            isSelected = dialogItemData.color == it.color
                        )
                    },
                    dialogType = dialogType,
                    dateTime = dialogItemData.dateTime,
                    itemTitle = dialogItemData.itemTitle,
                    itemDescription = dialogItemData.itemDescription,
                    needSetDefaultValue = false
                )
            } ?: itemDialogState.copy(
                dialogType = dialogType, needSetDefaultValue = false
            )
        }
    }

    fun sendEvent(dialogEvent: ItemDialogEvent) {
        when (dialogEvent) {
            is ItemDialogEvent.DismissEvent -> {
                sendUiEvent(UiEvent.NavigateUp)
                clearState()
            }
            is ItemDialogEvent.CreateEvent -> handleCreateEvent()
            is ItemDialogEvent.ItemDescriptionChangedEvent -> {
                itemDialogState = itemDialogState.copy(
                    itemDescription = dialogEvent.description
                )
            }

            is ItemDialogEvent.ItemTitleChangedEvent -> {
                itemDialogState = itemDialogState.copy(
                    itemTitle = dialogEvent.title
                )
            }

            is ItemDialogEvent.DateTimeChangedEvent -> {
                itemDialogState = itemDialogState.copy(
                    dateTime = dialogEvent.dateTime,
                    needShowDateTimePicker = false
                )
            }

            is ItemDialogEvent.ColorSelectionChangedEvent -> {
                itemDialogState = itemDialogState.copy(colorItems = itemDialogState.colorItems.map {
                    it.copy(
                        isSelected = dialogEvent.color == it.color
                    )
                })
            }

            is ItemDialogEvent.DateTimeClickedEvent -> {
                itemDialogState = itemDialogState.copy(
                    needShowDateTimePicker = true
                )
            }

            is ItemDialogEvent.DateTimePickerCancelEvent -> {
                itemDialogState = itemDialogState.copy(
                    needShowDateTimePicker = false
                )
            }
        }
    }

    fun clearState() {
        itemDialogState = ItemDialogState()
    }

    private fun handleCreateEvent() {
        if (itemDialogState.itemTitle.isBlank()) {
            itemDialogState = itemDialogState.copy(shouldShowErrorMessage = true)
        } else {
            itemDialogState = itemDialogState.copy(shouldShowErrorMessage = false)
            sendUiEvent(UiEvent.Navigate(""))
        }
    }
}
