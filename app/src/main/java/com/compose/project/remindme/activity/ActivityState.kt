package com.compose.project.remindme.activity

import com.compose.project.remindme.presentation.navigation.BottomNavigationItem
import com.compose.project.remindme.presentation.navigation.bottomNavigationItems

data class ActivityState(
    val bottomNavigationItemList: List<BottomNavigationItem> = bottomNavigationItems,
    val needRequestPermission: Boolean = true
)
