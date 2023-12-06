package com.compose.project.remindme.presentation.event

import com.compose.project.remindme.core.util.UiText

sealed class UiEvent {
    object NavigateUp: UiEvent()
    data class Navigate(val route: String): UiEvent()
    data class ShowSnackBar(val message: UiText): UiEvent()
}