package com.compose.project.remindme.presentation.archived

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.compose.project.remindme.presentation.component.AddButton

@Composable
fun ArchivedScreen(
    modifier: Modifier = Modifier,
    viewModel: ArchivedViewModel = hiltViewModel()
) {
    AddButton {

    }
}