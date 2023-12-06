package com.compose.project.remindme.presentation.note

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.compose.project.remindme.presentation.component.AddButton

@Composable
fun NoteScreen (
    modifier: Modifier = Modifier,
    viewModel: NoteViewModel = hiltViewModel()
) {
    AddButton() {

    }
}