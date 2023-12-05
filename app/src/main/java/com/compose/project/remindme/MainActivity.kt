package com.compose.project.remindme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.compose.project.remindme.presentation.archived.ArchivedScreen
import com.compose.project.remindme.presentation.navigation.BottomNavigationBar
import com.compose.project.remindme.presentation.navigation.Route
import com.compose.project.remindme.presentation.note.NoteScreen
import com.compose.project.remindme.presentation.reminder.ReminderScreen
import com.compose.project.remindme.ui.theme.RemindMeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RemindMeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val snackBarHostState = remember {
                        SnackbarHostState()
                    }
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        snackbarHost = {
                            SnackbarHost(snackBarHostState)
                        },
                        bottomBar = {
                            BottomNavigationBar(onNavigate = navController::navigate)
                        }
                    ) { padding ->
                        NavHost(
                            navController = navController,
                            startDestination = Route.NOTE,
                            modifier = Modifier.padding(padding)
                        ) {
                            composable(Route.NOTE) {
                                NoteScreen()
                            }
                            composable(Route.REMINDER) {
                                ReminderScreen()
                            }
                            composable(Route.ARCHIVED) {
                                ArchivedScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}