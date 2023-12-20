package com.compose.project.remindme.presentation.dialog.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.compose.project.remindme.R
import com.compose.project.remindme.presentation.component.DateTimePicker
import com.compose.project.remindme.presentation.component.ParseDateText
import com.compose.project.remindme.presentation.component.color.ColorItemsHorizontalList
import com.compose.project.remindme.presentation.event.UiEvent
import com.compose.project.remindme.ui.LocalDimension
import com.compose.project.remindme.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateEditItemDialog(
    modifier: Modifier = Modifier,
    itemDialogType: ItemDialogType,
    defaultDialogData: DialogItemData? = null,
    onCreateClick: (DialogItemData) -> Unit,
    onCancelClick: () -> Unit
) {
    val viewModel: ItemDialogViewModel = hiltViewModel()
    viewModel.setDefaultStateIfNeeded(defaultDialogData, itemDialogType)
    val itemDialogState = viewModel.itemDialogState
    val dimensions = LocalDimension.current
    val context = LocalContext.current

    LaunchedEffect(key1 = itemDialogState) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.Navigate -> {
                    val dialogItemData = DialogItemData(
                        color = itemDialogState.colorItems.first { it.isSelected }.color,
                        itemTitle = itemDialogState.itemTitle,
                        dateTime = itemDialogState.dateTime,
                        itemDescription = itemDialogState.itemDescription
                    )
                    viewModel.clearState()
                    onCreateClick(dialogItemData)
                }

                is UiEvent.NavigateUp -> {
                    onCancelClick()
                }

                else -> Unit
            }
        }
    }

    Dialog(
        onDismissRequest = { viewModel.sendEvent(ItemDialogEvent.DismissEvent) },
        properties = DialogProperties(dismissOnClickOutside = false),
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(dimensions.spaceSmall),
            shape = MaterialTheme.shapes.extraSmall
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(dimensions.spaceMedium),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = itemDialogState.dialogType.title.asString(context = context),
                    style = MaterialTheme.typography.displaySmall,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(dimensions.spaceMedium))
                TextField(
                    value = itemDialogState.itemTitle,
                    onValueChange = {
                        viewModel.sendEvent(ItemDialogEvent.ItemTitleChangedEvent(it))
                    },
                    textStyle = LocalTextStyle.current.copy(
                        textAlign = TextAlign.Left
                    ),
                    singleLine = true,
                    placeholder = {
                        Text(text = stringResource(id = R.string.enter_title))
                    },
                    supportingText = {
                        if (itemDialogState.shouldShowErrorMessage && itemDialogState.itemTitle.isBlank()) {
                            Text(text = stringResource(id = R.string.title_error_message))
                        }
                    },
                    isError = itemDialogState.shouldShowErrorMessage && itemDialogState.itemTitle.isBlank()
                )
                Spacer(modifier = Modifier.height(dimensions.spaceSmall))
                TextField(
                    value = itemDialogState.itemDescription,
                    onValueChange = {
                        viewModel.sendEvent(ItemDialogEvent.ItemDescriptionChangedEvent(it))
                    },
                    textStyle = LocalTextStyle.current.copy(
                        textAlign = TextAlign.Left
                    ),
                    maxLines = 3,
                    placeholder = {
                        Text(text = stringResource(id = R.string.enter_description))
                    }
                )
                if (itemDialogState.dialogType == ItemDialogType.ReminderDialog) {
                    Spacer(modifier = Modifier.height(dimensions.spaceSmall))
                    Row(
                        modifier = Modifier
                            .clickable {
                                viewModel.sendEvent(ItemDialogEvent.DateTimeClickedEvent)
                            }
                            .padding(dimensions.spaceMedium),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.weight(1f),
                            text = ParseDateText(dateTime = itemDialogState.dateTime),
                            textAlign = TextAlign.Start
                        )
                        Icon(imageVector = Icons.Default.DateRange, contentDescription = "")
                    }
                }
                Spacer(modifier = Modifier.height(dimensions.spaceSmall))
                ColorItemsHorizontalList(
                    colorItems = itemDialogState.colorItems,
                    onColorItemClick = {
                        viewModel.sendEvent(ItemDialogEvent.ColorSelectionChangedEvent(it))
                    }
                )
                Spacer(modifier = Modifier.height(dimensions.spaceMedium))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Button(
                        onClick = { viewModel.sendEvent(ItemDialogEvent.CreateEvent) },
                    ) {
                        Text(
                            stringResource(
                                id = if (defaultDialogData == null) {
                                    R.string.crate
                                } else R.string.update
                            )
                        )
                    }
                    Spacer(modifier = Modifier.width(dimensions.spaceMedium))
                    Button(
                        onClick = { viewModel.sendEvent(ItemDialogEvent.DismissEvent) },
                    ) {
                        Text(stringResource(id = R.string.cancel))
                    }
                }
            }
        }
        if (itemDialogState.needShowDateTimePicker) {
            DateTimePicker(
                onDateTimeSelect = {
                    viewModel.sendEvent(ItemDialogEvent.DateTimeChangedEvent(it))
                },
                onCancelClick = {
                    viewModel.sendEvent(ItemDialogEvent.DateTimePickerCancelEvent)
                })
        }
    }
}