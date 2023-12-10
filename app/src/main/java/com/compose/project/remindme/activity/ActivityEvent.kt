package com.compose.project.remindme.activity

import com.compose.project.remindme.presentation.navigation.BottomNavigationItem

sealed class ActivityEvent {
    data class BottomNavigationItemClickEvent(val bottomNavigationItem: BottomNavigationItem) :
        ActivityEvent()
}
