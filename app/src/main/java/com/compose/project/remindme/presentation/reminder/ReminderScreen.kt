package com.compose.project.remindme.presentation.reminder

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.compose.project.remindme.presentation.component.AddButton

@Composable
fun ReminderScreen (
    modifier: Modifier = Modifier,
    viewModel: ReminderViewModel = hiltViewModel()
) {
    AddButton {

    }
}