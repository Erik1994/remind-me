package com.compose.project.remindme.presentation.reminder

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.compose.project.remindme.presentation.common.ScreenBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReminderViewModel @Inject constructor() : ScreenBaseViewModel() {
    var reminderState: ReminderState by mutableStateOf(ReminderState())
        private set

    fun sendEvent(reminderEvent: ReminderEvent) {
        when(reminderEvent) {
            is ReminderEvent.AddButtonClickEvent -> {
                reminderState = reminderState.copy(showCreateNoteDialog = true)
            }
            is ReminderEvent.DialogCancelClickEvent -> {
                reminderState = reminderState.copy(showCreateNoteDialog = false)
            }
        }
    }
}