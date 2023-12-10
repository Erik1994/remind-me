package com.compose.project.remindme.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.compose.project.remindme.R
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun ParseDateText(
    dateTime: LocalDateTime
): String {
    val today = LocalDate.now()
    val time = dateTime.toLocalTime()
    return when (val date = dateTime.toLocalDate()) {
        today -> "${stringResource(id = R.string.today)}, ${
            DateTimeFormatter.ofPattern("HH:mm").format(time)
        }"

        today.minusDays(1) -> "${stringResource(id = R.string.yesterday)}, ${
            DateTimeFormatter.ofPattern(
                "HH:mm"
            ).format(time)
        }"

        today.plusDays(1) -> "${stringResource(id = R.string.tomorrow)}, ${
            DateTimeFormatter.ofPattern(
                "HH:mm"
            ).format(time)
        }"

        else -> "${
            DateTimeFormatter.ofPattern("d MMM yy").format(date)
        }, ${DateTimeFormatter.ofPattern("HH:mm").format(time)}"
    }
}