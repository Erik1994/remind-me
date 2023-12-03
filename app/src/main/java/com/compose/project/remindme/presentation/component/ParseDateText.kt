package com.compose.project.remindme.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.compose.project.remindme.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun ParseDateText(
    date: LocalDate
): String {
    val today = LocalDate.now()
    return when(date) {
        today -> "${stringResource(id = R.string.today)}, ${DateTimeFormatter.ofPattern("HH:mm").format(date)}"
        today.minusDays(1) -> "${stringResource(id = R.string.yesterday)}, ${DateTimeFormatter.ofPattern("HH:mm").format(date)}"
        today.plusDays(1) -> "${stringResource(id = R.string.tomorrow)}, ${DateTimeFormatter.ofPattern("HH:mm").format(date)}"
        else -> DateTimeFormatter.ofPattern("d MMM yy, HH:mm").format(date)
    }
}