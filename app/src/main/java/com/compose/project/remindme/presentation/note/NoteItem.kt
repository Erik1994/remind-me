package com.compose.project.remindme.presentation.note

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.compose.project.remindme.domain.model.NoteData
import com.compose.project.remindme.presentation.component.DateTimeText
import com.compose.project.remindme.presentation.component.ParseDateText
import com.compose.project.remindme.presentation.extension.isColorLight
import com.compose.project.remindme.ui.LocalDimension
import com.compose.project.remindme.ui.theme.Black
import com.compose.project.remindme.ui.theme.RemindMeTheme
import com.compose.project.remindme.ui.theme.White
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
fun NoteItem(
    modifier: Modifier = Modifier,
    noteData: NoteData,
    onItemClick: (NoteData) -> Unit,
    onDeleteClick: (NoteData) -> Unit
) {
    val dimensions = LocalDimension.current
    val colorOnBackground = if (noteData.color.isColorLight()) {
        Black
    } else White
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                color = noteData.color,
                shape = MaterialTheme.shapes.small
            )
            .padding(dimensions.spaceSmall)
            .clickable {
                onItemClick(noteData)
            },
        shape = MaterialTheme.shapes.small
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = noteData.color,
                    shape = MaterialTheme.shapes.small
                )
                .fillMaxWidth()
                .padding(dimensions.spaceSmall)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f),
                    text = noteData.title,
                    style = MaterialTheme.typography.headlineLarge,
                    color = colorOnBackground
                )
                Icon(
                    modifier = Modifier.clickable {
                        onDeleteClick(noteData)
                    },
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "",
                    tint = colorOnBackground
                )
            }
            Spacer(modifier = Modifier.height(dimensions.spaceSmall))
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = noteData.description,
                style = MaterialTheme.typography.headlineMedium,
                color = colorOnBackground
            )
            Spacer(modifier = Modifier.height(dimensions.spaceMedium))
            DateTimeText(
                dateTime = ParseDateText(dateTime = noteData.localDate),
                backGroundColor = noteData.color
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun NoteItemPreview() {
    RemindMeTheme {
        NoteItem(
            noteData = NoteData(
                color = Color.Gray,
                title = "Quick note",
                description = "Some note to use later",
                localDate = LocalDateTime.now()
            ),
            onDeleteClick = {  },
            onItemClick = { }
        )
    }
}