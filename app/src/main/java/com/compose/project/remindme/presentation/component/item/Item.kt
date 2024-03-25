package com.compose.project.remindme.presentation.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Timelapse
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.LockOpen
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.project.remindme.R
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
import com.compose.project.remindme.ui.theme.TextWhite
import com.compose.project.remindme.ui.theme.White
import java.time.LocalDateTime

@Composable
fun Item(
    modifier: Modifier = Modifier,
    itemData: ItemData,
    onItemClick: (ItemData) -> Unit,
    onDeleteClick: (ItemData) -> Unit,
    onLockClick: (ItemData) -> Unit
) {
    val dimensions = LocalDimension.current
    val (colorOnBackground, animationColor) = if (itemData.color.isColorLight()) {
        Black to Grey20
    } else {
        White to Color.Cyan
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                color = itemData.color,
                shape = MaterialTheme.shapes.small
            )
            .padding(dimensions.spaceSmall)
            .clip(MaterialTheme.shapes.small),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onLockClick(itemData)
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(
                    id = if (itemData.isLocked) {
                        R.string.unlock
                    } else {
                        R.string.lock
                    }
                ),
                color = colorOnBackground,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(dimensions.spaceExtraSmall))
            Icon(
                modifier = Modifier,
                imageVector = if (itemData.isLocked) {
                    Icons.Rounded.LockOpen
                } else {
                    Icons.Rounded.Lock
                },
                contentDescription = "",
                tint = colorOnBackground
            )
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    color = itemData.color,
                    shape = MaterialTheme.shapes.small
                )
                .padding(dimensions.spaceSmall)
                .blur(
                    radius = if (itemData.isLocked) {
                        5.dp
                    } else {
                        0.dp
                    },
                    edgeTreatment = BlurredEdgeTreatment.Rectangle
                )
                .clickable {
                    if (!itemData.isLocked) {
                        onItemClick(itemData)
                    }
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
                    DateTimeText(
                        dateTime = ParseDateText(dateTime = itemData.localDate),
                        backGroundColor = itemData.color
                    )
                } else {
                    DateTimeText(
                        dateTime = ParseDateText(dateTime = itemData.localDate),
                        backGroundColor = itemData.color
                    )
                }
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
            onItemClick = { },
            onLockClick = { }
        )
    }
}