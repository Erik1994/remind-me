package com.compose.project.remindme.presentation.component.item

import androidx.compose.animation.AnimatedContent
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
import androidx.compose.ui.unit.Dp
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
            AnimatedContent(targetState = itemData.isLocked, label = "") { isLocked ->
                if (isLocked) {
                    Text(
                        text = stringResource(R.string.unlock),
                        color = colorOnBackground,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                } else {
                    Text(
                        text = stringResource(R.string.lock),
                        color = colorOnBackground,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.width(dimensions.spaceExtraSmall))
            AnimatedContent(targetState = itemData.isLocked, label = "") { isLocked ->
                if (isLocked) {
                    Icon(
                        modifier = Modifier,
                        imageVector = Icons.Rounded.LockOpen,
                        contentDescription = "",
                        tint = colorOnBackground
                    )
                } else {
                    Icon(
                        modifier = Modifier,
                        imageVector = Icons.Rounded.Lock,
                        contentDescription = "",
                        tint = colorOnBackground
                    )
                }
            }
            if (itemData !is NoteData && !itemData.isCompleted) {
                Spacer(modifier = Modifier.width(dimensions.spaceExtraSmall))
                AnimatedContent(targetState = itemData.isLocked, label = "") { locked ->
                    if (locked) {
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
            }
        }

        AnimatedContent(targetState = itemData.isLocked, label = "") { isLocked ->
            if (isLocked) {
                ItemCard(
                    blurRadius = 7.dp,
                    itemData = itemData,
                    colorOnBackground = colorOnBackground,
                    animationColor = animationColor,
                    onItemClick = onItemClick,
                    onDeleteClick = onDeleteClick
                )
            } else {
                ItemCard(
                    itemData = itemData,
                    colorOnBackground = colorOnBackground,
                    animationColor = animationColor,
                    onItemClick = onItemClick,
                    onDeleteClick = onDeleteClick
                )
            }
        }
    }
}

@Composable
private fun ItemCard(
    modifier: Modifier = Modifier,
    blurRadius: Dp = 0.dp,
    itemData: ItemData,
    colorOnBackground: Color,
    animationColor: Color,
    onItemClick: (ItemData) -> Unit,
    onDeleteClick: (ItemData) -> Unit,
) {
    val dimensions = LocalDimension.current
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                color = itemData.color,
                shape = MaterialTheme.shapes.small
            )
            .padding(dimensions.spaceSmall)
            .blur(
                radius = blurRadius,
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
                        AnimatedContent(targetState = blurRadius, label = "") { radius ->
                            if (radius == 0.dp) {
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
                    }
                }
                Icon(
                    modifier = Modifier.clickable {
                        if (!itemData.isLocked) {
                            onDeleteClick(itemData)
                        }
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