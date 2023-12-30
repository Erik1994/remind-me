package com.compose.project.remindme.presentation.component.item

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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Timelapse
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.compose.project.remindme.core.ui.ParseDateText
import com.compose.project.remindme.domain.model.ItemData
import com.compose.project.remindme.domain.model.NoteData
import com.compose.project.remindme.presentation.component.AnimatedBorderCard
import com.compose.project.remindme.presentation.component.DateTimeText
import com.compose.project.remindme.presentation.extension.isColorLight
import com.compose.project.remindme.ui.LocalDimension
import com.compose.project.remindme.ui.theme.Black
import com.compose.project.remindme.ui.theme.Grey20
import com.compose.project.remindme.ui.theme.RemindMeTheme
import com.compose.project.remindme.ui.theme.White
import java.time.LocalDateTime

@Composable
fun Item(
    modifier: Modifier = Modifier,
    itemData: ItemData,
    onItemClick: (ItemData) -> Unit,
    onDeleteClick: (ItemData) -> Unit
) {
    val dimensions = LocalDimension.current
    val (colorOnBackground, animationColor) = if (itemData.color.isColorLight()) {
        Black to Grey20
    } else {
        White to Color.Cyan
    }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                color = itemData.color,
                shape = MaterialTheme.shapes.small
            )
            .padding(dimensions.spaceSmall)
            .clickable {
                onItemClick(itemData)
            },
        shape = MaterialTheme.shapes.small
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = itemData.color,
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
                    text = itemData.title,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = colorOnBackground
                )
                if (itemData !is NoteData) {
                    if (itemData.isCompleted) {
                        Icon(
                            Icons.Filled.CheckCircle,
                            contentDescription = "",
                            tint = colorOnBackground
                        )
                    } else {
                        AnimatedBorderCard(
                            gradient = Brush.sweepGradient(
                                listOf(animationColor, itemData.color)
                            ),
                            color = itemData.color
                        ) {
                            Icon(
                                Icons.Default.Timelapse,
                                contentDescription = "",
                                tint = colorOnBackground
                            )
                        }
                    }
                }
                Icon(
                    modifier = Modifier.clickable {
                        onDeleteClick(itemData)
                    },
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "",
                    tint = colorOnBackground
                )
            }
            Spacer(modifier = Modifier.height(dimensions.spaceMedium))
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = itemData.description,
                style = MaterialTheme.typography.headlineMedium,
                color = colorOnBackground
            )
            Spacer(modifier = Modifier.height(dimensions.spaceMedium))
            if (itemData !is NoteData && !itemData.isCompleted) {
                AnimatedBorderCard(
                    gradient = Brush.sweepGradient(
                        listOf(animationColor, itemData.color)
                    ),
                    shape = MaterialTheme.shapes.extraSmall
                ) {
                    DateTimeText(
                        dateTime = ParseDateText(dateTime = itemData.localDate),
                        backGroundColor = itemData.color
                    )
                }
            } else {
                DateTimeText(
                    dateTime = ParseDateText(dateTime = itemData.localDate),
                    backGroundColor = itemData.color
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun NoteItemPreview() {
    RemindMeTheme {
        Item(
            itemData = NoteData(
                color = Color.Gray,
                title = "Quick note",
                description = "Some note to use later",
                localDate = LocalDateTime.now()
            ),
            onDeleteClick = { },
            onItemClick = { }
        )
    }
}