package com.compose.project.remindme.presentation.note

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.compose.project.remindme.R
import com.compose.project.remindme.presentation.biometric.BiometricManager
import com.compose.project.remindme.presentation.component.AddButton
import com.compose.project.remindme.presentation.component.EmptyScreenPlaceHolder
import com.compose.project.remindme.presentation.component.ScreenHeader
import com.compose.project.remindme.presentation.component.item.ItemEvent
import com.compose.project.remindme.presentation.component.item.ItemsGrid
import com.compose.project.remindme.presentation.dialog.item.CreateEditItemDialog
import com.compose.project.remindme.presentation.dialog.item.ItemDialogType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    modifier: Modifier = Modifier,
    viewModel: NoteViewModel = hiltViewModel(),
    biometricResult: BiometricManager.BiometricResult?,
    onResetState: () -> Unit,
    onBiometricOpen: () -> Unit
) {
    val noteState = viewModel.noteState

    LaunchedEffect(key1 = biometricResult) {
        if (biometricResult is BiometricManager.BiometricResult.AuthenticationSuccess) {
            viewModel.toggleItemLockState()
        } else if (biometricResult != null) {
            viewModel.resetLockUnlockItemId()
        }
        onResetState()
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            AddButton {
                viewModel.sendEvent(NoteEvent.AddButtonClickEvent)
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            ScreenHeader(title = stringResource(id = R.string.notes))
            if (noteState.categorizedItems.isEmpty()) {
                EmptyScreenPlaceHolder(message = R.string.empty_note_message)
            }
            ItemsGrid(
                categorizedItems = noteState.categorizedItems,
                onItemClick = {
                    viewModel.sendItemEvent(ItemEvent.ItemClickEvent(it))
                },
                onDeleteClick = {
                    viewModel.sendItemEvent(ItemEvent.ItemDeleteClickEvent(it))
                },
                onLockClick = {
                    viewModel.sendEvent(NoteEvent.OnLockClickEvent(it.id))
                    if (it.isLocked) {
                        onBiometricOpen()
                    } else {
                        viewModel.toggleItemLockState()
                    }
                }
            )
            AnimatedVisibility(
                visible = noteState.showCreateNoteDialog,
                exit = fadeOut() + shrinkVertically(shrinkTowards = Alignment.Top)
            ) {
                CreateEditItemDialog(
                    itemDialogType = ItemDialogType.NoteDialog,
                    onCreateClick = {
                        viewModel.sendEvent(NoteEvent.DialogCreateClickEvent(it))
                    },
                    onCancelClick = {
                        viewModel.sendEvent(NoteEvent.DialogCancelClickEvent)
                    },
                    defaultDialogData = noteState.dialogItemData
                )
            }
        }
    }
}