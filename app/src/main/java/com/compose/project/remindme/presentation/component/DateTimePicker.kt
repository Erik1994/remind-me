package com.compose.project.remindme.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.compose.project.remindme.R
import com.compose.project.remindme.ui.theme.Black
import com.compose.project.remindme.ui.theme.Green10
import com.compose.project.remindme.ui.theme.Green20
import com.compose.project.remindme.ui.theme.Green30
import com.compose.project.remindme.ui.theme.TextWhite
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerColors
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.TimePickerColors
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Composable
fun DateTimePicker(
    onDateTimeSelect: (LocalDateTime) -> Unit,
    onCancelClick: () -> Unit
) {

    var pickedDate by remember {
        mutableStateOf(LocalDate.now())
    }
    var pickedTime by remember {
        mutableStateOf(LocalTime.NOON)
    }

    val dateDialogState = rememberMaterialDialogState()
    val timeDialogState = rememberMaterialDialogState()
    MaterialDialog(
        dialogState = dateDialogState,
        properties = DialogProperties(
            dismissOnClickOutside = false
        ),
        backgroundColor = MaterialTheme.colorScheme.surface,
        buttons = {
            positiveButton(
                text = stringResource(id = R.string.ok), textStyle = TextStyle(
                    color = TextWhite
                )
            ) {
                timeDialogState.show()
            }
            negativeButton(
                text = stringResource(id = R.string.cancel), textStyle = TextStyle(
                    color = TextWhite
                )
            ) {
                onCancelClick()
            }
        }
    ) {
        datepicker(
            initialDate = LocalDate.now(),
            title = stringResource(id = R.string.date_picker),
            allowedDateValidator = {
                it.dayOfMonth >= LocalDate.now().dayOfMonth
            },
            colors = object : DatePickerColors {
                override val calendarHeaderTextColor: Color
                    get() = TextWhite
                override val headerBackgroundColor: Color
                    get() = Green10
                override val headerTextColor: Color
                    get() = TextWhite

                @Composable
                override fun dateBackgroundColor(active: Boolean): State<Color> {
                    val color = if (active) {
                        Green30
                    } else {
                        Green10
                    }
                    return mutableStateOf(color)
                }

                @Composable
                override fun dateTextColor(active: Boolean): State<Color> {
                    return mutableStateOf(TextWhite)
                }

            }
        ) {
            pickedDate = it
        }
    }
    MaterialDialog(
        dialogState = timeDialogState,
        properties = DialogProperties(
            dismissOnClickOutside = false
        ),
        backgroundColor = MaterialTheme.colorScheme.surface,
        buttons = {
            positiveButton(
                text = stringResource(id = R.string.ok), textStyle = TextStyle(
                    color = TextWhite
                )
            ) {
                onDateTimeSelect(
                    LocalDateTime.of(
                        pickedDate,
                        pickedTime
                    )
                )
            }
            negativeButton(
                text = stringResource(id = R.string.cancel), textStyle = TextStyle(
                    color = TextWhite
                )
            ) {
                onCancelClick()
            }
        }
    ) {
        timepicker(
            is24HourClock = true,
            initialTime = LocalTime.NOON,
            title = stringResource(id = R.string.time_picker),
            colors = object : TimePickerColors {
                override val border: BorderStroke
                    get() = BorderStroke(0.dp, Green30)

                @Composable
                override fun backgroundColor(active: Boolean): State<Color> {
                    val color = if (active) {
                        Green30
                    } else {
                        Green10
                    }
                    return mutableStateOf(color)
                }

                override fun headerTextColor(): Color {
                    return TextWhite
                }

                @Composable
                override fun periodBackgroundColor(active: Boolean): State<Color> {
                    return mutableStateOf(Green10)
                }

                override fun selectorColor(): Color {
                    return Green30
                }

                override fun selectorTextColor(): Color {
                    return TextWhite
                }

                @Composable
                override fun textColor(active: Boolean): State<Color> {
                    return mutableStateOf(TextWhite)
                }

            }
        ) {
            pickedTime = it
        }
    }
    if (!dateDialogState.showing && !timeDialogState.showing) {
        dateDialogState.show()
    }
}