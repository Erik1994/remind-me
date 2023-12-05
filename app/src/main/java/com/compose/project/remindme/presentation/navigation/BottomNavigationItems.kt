package com.compose.project.remindme.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.List
import androidx.compose.ui.graphics.vector.ImageVector
import com.compose.project.remindme.R
import com.compose.project.remindme.core.util.UiText

data class BottomNavigationItem(
    val route: String,
    val title: UiText,
    val hasNews: Boolean,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val badgeCount: Int? = null
)

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        route = Route.NOTE,
        title = UiText.StringResource(R.string.note),
        hasNews = false,
        selectedIcon = Icons.Filled.List,
        unSelectedIcon = Icons.Outlined.List
    ),
    BottomNavigationItem(
        route = Route.REMINDER,
        title = UiText.StringResource(R.string.reminder),
        hasNews = false,
        selectedIcon = Icons.Filled.DateRange,
        unSelectedIcon = Icons.Outlined.DateRange
    ),
    BottomNavigationItem(
        route = Route.ARCHIVED,
        title = UiText.StringResource(R.string.archived),
        hasNews = false,
        selectedIcon = Icons.Filled.Done,
        unSelectedIcon = Icons.Outlined.Done
    )
)