package com.compose.project.remindme.presentation.note

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.compose.project.remindme.presentation.component.AddButton
import com.compose.project.remindme.presentation.dialog.itemdialog.CreateEditItemDialog
import com.compose.project.remindme.presentation.dialog.itemdialog.ItemDialogType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    modifier: Modifier = Modifier,
    viewModel: NoteViewModel = hiltViewModel()
) {
    val noteState = viewModel.noteState
    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            AddButton {
                viewModel.sendEvent(NoteEvent.AddButtonClickEvent)
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            if (noteState.showCreateNoteDialog) {
                CreateEditItemDialog(
                    itemDialogType = ItemDialogType.NoteDialog,
                    onCreateClick = {},
                    onCancelClick = {viewModel.sendEvent(NoteEvent.CancelClickEvent)}
                )
            }
        }
    }
}