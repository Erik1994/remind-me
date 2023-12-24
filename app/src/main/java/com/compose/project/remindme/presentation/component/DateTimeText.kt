package com.compose.project.remindme.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.compose.project.remindme.presentation.extension.isColorLight
import com.compose.project.remindme.ui.LocalDimension
import com.compose.project.remindme.ui.theme.Black
import com.compose.project.remindme.ui.theme.RemindMeTheme
import com.compose.project.remindme.ui.theme.White

@Composable
fun DateTimeText(
    modifier: Modifier = Modifier,
    dateTime: String,
    backGroundColor: Color,
    borderWidth: Dp = 1.5.dp,
    style: TextStyle = MaterialTheme.typography.headlineSmall,
) {
    val dimensions = LocalDimension.current
    val borderColor = if (backGroundColor.isColorLight()) {
        Black
    } else White
    Box(
        modifier = modifier
            .background(
                color = backGroundColor,
                shape = MaterialTheme.shapes.extraSmall
            )
            .clip(MaterialTheme.shapes.extraSmall)
            .border(
                width = borderWidth,
                color = borderColor,
                shape = MaterialTheme.shapes.extraSmall
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(dimensions.spaceSmall),
            text = dateTime,
            color = borderColor,
            style = style
        )
    }
}

@Composable
@Preview(showBackground = true)
fun DateTimeTextPreview() {
    RemindMeTheme {
        DateTimeText(
            dateTime = "Today, 4:30",
            backGroundColor = Color.Green
        )
    }
}