package com.compose.project.remindme.presentation.reminder

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
fun ReminderScreen (
    modifier: Modifier = Modifier,
    viewModel: ReminderViewModel = hiltViewModel()
) {
    val reminderState = viewModel.reminderState
    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            AddButton {
                viewModel.sendEvent(ReminderEvent.AddButtonClickEvent)
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            if (reminderState.showCreateNoteDialog) {
                CreateEditItemDialog(
                    itemDialogType = ItemDialogType.ReminderDialog,
                    onCreateClick = {},
                    onCancelClick = {viewModel.sendEvent(ReminderEvent.DialogCancelClickEvent)}
                )
            }
        }
    }
}