package com.compose.project.remindme.presentation.event

import com.compose.project.remindme.core.util.UiText

sealed class UiEvent {
    object Navigate: UiEvent()
    object NavigateUp: UiEvent()
    data class ShowSnackBar(val message: UiText)
}