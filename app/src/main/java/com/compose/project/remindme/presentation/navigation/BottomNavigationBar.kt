package com.compose.project.remindme.presentation.navigation

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(
    itemList: List<BottomNavigationItem>,
    onNavigate: (BottomNavigationItem) -> Unit
) {
    val context = LocalContext.current
    NavigationBar {
        itemList.forEach { bottomNavigationItem ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                        LocalAbsoluteTonalElevation.current
                    ),
                    unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurface
                ),
                selected = bottomNavigationItem.isSelected,
                onClick = {
                    onNavigate(bottomNavigationItem)
                },
                label = {
                    Text(text = bottomNavigationItem.title.asString(context))
                },
                icon = {
                    BadgedBox(
                        badge = {
                            if (bottomNavigationItem.badgeCount != null) {
                                Badge {
                                    Text(text = bottomNavigationItem.badgeCount.toString())
                                }
                            } else if (bottomNavigationItem.hasNews) {
                                Badge()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (bottomNavigationItem.isSelected) {
                                bottomNavigationItem.selectedIcon
                            } else bottomNavigationItem.unSelectedIcon,
                            contentDescription = bottomNavigationItem.title.asString(context)
                        )
                    }
                }
            )
        }
    }
}