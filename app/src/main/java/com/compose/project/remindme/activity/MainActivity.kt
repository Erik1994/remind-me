package com.compose.project.remindme.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.compose.project.remindme.core.ui.ObserveAsEventsWithSuspendBlock
import com.compose.project.remindme.core.ui.PermissionTextProvider
import com.compose.project.remindme.core.ui.enums.PermissionsEnum
import com.compose.project.remindme.core.util.orDefault
import com.compose.project.remindme.data.manager.notification.ReminderNotificationManager
import com.compose.project.remindme.data.manager.notification.ReminderNotificationManager.Companion.ARCHIVED_ROUT_KEY
import com.compose.project.remindme.presentation.archived.ArchivedScreen
import com.compose.project.remindme.presentation.component.BuildMultiplePermissionsActivityContract
import com.compose.project.remindme.presentation.dialog.permission.PermissionDialog
import com.compose.project.remindme.presentation.event.UiEvent
import com.compose.project.remindme.presentation.extension.openAppSettings
import com.compose.project.remindme.presentation.navigation.BottomNavigationBar
import com.compose.project.remindme.presentation.navigation.Route
import com.compose.project.remindme.presentation.note.NoteScreen
import com.compose.project.remindme.presentation.reminder.ReminderScreen
import com.compose.project.remindme.ui.theme.RemindMeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var activityResultContract: ManagedActivityResultLauncher<String, Boolean>? = null
    private var activityResultContractForMultiplePermissions: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>? =
        null
    private val permissionsToRequest = PermissionsEnum.getPermissionArray()
    private var activityViewModel: ActivityViewModel? = null

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
                    val context = LocalContext.current
                    val navController = rememberNavController()
                    val snackBarHostState = remember {
                        SnackbarHostState()
                    }
                    val viewModel: ActivityViewModel = hiltViewModel()
                    activityViewModel = viewModel
                    val activityState = viewModel.activityState
                    val dialogQueue = viewModel.visiblePermissionDialogQueue

                    // In case of multiplePermissions

                    BuildMultiplePermissionsActivityContract(
                        onPermissionLauncher = { activityContract ->
                            activityResultContractForMultiplePermissions = activityContract
                            SideEffect {
                                if (activityState.needRequestPermission) {
                                    activityContract.launch(permissionsToRequest)
                                }
                            }
                            dialogQueue
                                .reversed()
                                .forEach { permission ->
                                    PermissionDialog(
                                        permissionTextProvider = PermissionTextProvider.getPermissionTextProvider(
                                            permission
                                        ),
                                        // You can relay on shouldShowRequestPermissionRationale if before showing the dialog
                                        // the permission has been requested at least once
                                        isPermanentlyDeclined = !shouldShowRequestPermissionRationale(
                                            permission.permission
                                        ),
                                        onDismiss = {
                                            viewModel.sendEvent(ActivityEvent.DismissPermissionDialogEvent)
                                        },
                                        onOkClick = {
                                            viewModel.dismissPermissionDialog()
                                            activityContract.launch(permissionsToRequest)
                                        },
                                        onGoToAppSettingsClick = {
                                            openAppSettings()
                                            viewModel.dismissPermissionDialog()
                                        }
                                    )
                                }
                        },
                        onResult = { map ->
                            permissionsToRequest.forEach { permission ->
                                viewModel.sendEvent(
                                    ActivityEvent.OnPermissionResultEvent(
                                        permission = PermissionsEnum.getPermissionByValue(
                                            permission
                                        ),
                                        isGranted = map[permission].orDefault(false)
                                    )
                                )
                            }
                        }
                    )


                    intent?.let {
                        handleIntent(it)
                    }

                    //In case of single permission
//                    BuildSinglePermissionActivityContract(
//                        onPermissionLauncher = { activityContract ->
//                            activityResultContract = activityContract
//                            SideEffect {
//                                if (activityState.needRequestPermission) {
//                                    activityContract.launch(PermissionsEnum.NOTIFICATION.permission)
//                                }
//                            }
//                            dialogQueue
//                                .reversed()
//                                .forEach { permission ->
//                                    PermissionDialog(
//                                        permissionTextProvider = PermissionTextProvider.getPermissionTextProvider(
//                                            PermissionsEnum.NOTIFICATION
//                                        ),
//                                        // You can relay on shouldShowRequestPermissionRationale if before showing the dialog
//                                        // the permission has been requested at least once
//                                        isPermanentlyDeclined = !shouldShowRequestPermissionRationale(
//                                            permission.permission
//                                        ),
//                                        onDismiss = {
//                                            viewModel.sendEvent(ActivityEvent.DismissPermissionDialogEvent)
//                                        },
//                                        onOkClick = {
//                                            viewModel.dismissPermissionDialog()
//                                            activityContract.launch(PermissionsEnum.NOTIFICATION.permission)
//                                        },
//                                        onGoToAppSettingsClick = {
//                                            openAppSettings()
//                                            viewModel.dismissPermissionDialog()
//                                        }
//                                    )
//                                }
//                        },
//                        onResult = { isGranted ->
//                            viewModel.sendEvent(
//                                ActivityEvent.OnPermissionResultEvent(
//                                    permission = PermissionsEnum.NOTIFICATION,
//                                    isGranted = isGranted
//                                )
//                            )
//                        }
//                    )

                    ObserveAsEventsWithSuspendBlock(flow = viewModel.uiEvent) {
                        when (it) {
                            is UiEvent.Navigate -> navController.navigate(it.route)
                            is UiEvent.ShowSnackBar -> snackBarHostState.showSnackbar(
                                message = it.message.asString(
                                    context
                                )
                            )

                            else -> navController.navigateUp()
                        }
                    }
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        snackbarHost = {
                            SnackbarHost(snackBarHostState)
                        },
                        bottomBar = {
                            BottomNavigationBar(itemList = activityState.bottomNavigationItemList) {
                                viewModel.sendEvent(ActivityEvent.BottomNavigationItemClickEvent(it))
                            }
                        }
                    ) { padding ->
                        NavHost(
                            navController = navController,
                            startDestination = activityState.defaultRoute,
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

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let {
            handleIntent(it)
        }
    }

    private fun handleIntent(intent: Intent) {
        if (intent.action == ReminderNotificationManager.REMINDER_NOTIFICATION_CLICK_ACTION) {
            val route = intent.getStringExtra(ARCHIVED_ROUT_KEY).orDefault(Route.NOTE)
            activityViewModel?.sendEvent(ActivityEvent.ReminderNotificationClickEvent(route))
        }
    }

    override fun onStart() {
        super.onStart()
        //activityResultContract?.launch(PermissionsEnum.NOTIFICATION.permission)
        activityResultContractForMultiplePermissions?.launch(permissionsToRequest)
    }
}