package com.compose.project.remindme.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.compose.project.remindme.R
import com.compose.project.remindme.ui.LocalDimension

@Composable
fun EmptyScreenPlaceHolder(
    modifier: Modifier = Modifier,
    message: Int
) {
    val dimensions = LocalDimension.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensions.spaceMedium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = modifier
                .width(400.dp)
                .height(400.dp),
            shape = MaterialTheme.shapes.small
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(MaterialTheme.shapes.extraSmall),
                painter = painterResource(id = R.drawable.reminder_image),
                contentScale = ContentScale.FillBounds,
                contentDescription = ""
            )
        }
        Spacer(modifier = Modifier.height(dimensions.spaceLarge))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = message),
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}