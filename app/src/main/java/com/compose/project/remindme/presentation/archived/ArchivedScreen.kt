package com.compose.project.remindme.presentation.archived

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.compose.project.remindme.R
import com.compose.project.remindme.presentation.biometric.BiometricManager
import com.compose.project.remindme.presentation.component.EmptyScreenPlaceHolder
import com.compose.project.remindme.presentation.component.ScreenHeader
import com.compose.project.remindme.presentation.component.item.ItemEvent
import com.compose.project.remindme.presentation.component.item.ItemsGrid
import com.compose.project.remindme.presentation.note.NoteEvent
import com.compose.project.remindme.ui.LocalDimension

@Composable
fun ArchivedScreen(
    modifier: Modifier = Modifier,
    viewModel: ArchivedViewModel = hiltViewModel(),
    biometricResult: BiometricManager.BiometricResult?,
    onResetState: () -> Unit,
    onBiometricOpen: () -> Unit,
) {
    val archivedState = viewModel.archivedState
    LaunchedEffect(key1 = biometricResult) {
        if (biometricResult is BiometricManager.BiometricResult.AuthenticationSuccess) {
            viewModel.toggleItemLockState()
        } else if (biometricResult != null){
            viewModel.resetLockUnlockItemId()
        }
        onResetState()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ScreenHeader(title = stringResource(id = R.string.archived))
        if (archivedState.categorizedItems.isEmpty()) {
            EmptyScreenPlaceHolder(message = R.string.empty_completed_message)
        }
        ItemsGrid(
            categorizedItems = archivedState.categorizedItems,
            onItemClick = {
                viewModel.sendItemEvent(ItemEvent.ItemClickEvent(it))
            },
            onDeleteClick = {
                viewModel.sendItemEvent(ItemEvent.ItemDeleteClickEvent(it))
            },
            onLockClick = {
                viewModel.sendEvent(ArchivedEvent.OnLockClickEvent(it.id))
                if (it.isLocked) {
                    onBiometricOpen()
                } else {
                    viewModel.toggleItemLockState()
                }
            }
        )
    }
}