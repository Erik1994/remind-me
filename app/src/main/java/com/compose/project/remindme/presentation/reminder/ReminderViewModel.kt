package com.compose.project.remindme.presentation.reminder

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.compose.project.remindme.data.mapper.DIALOG_ITEM_TO_REMINDER_DATA_MAPPER
import com.compose.project.remindme.data.mapper.ITEM_DATA_TO_DIALOG_ITEM_MAPPER
import com.compose.project.remindme.domain.CancelScheduledReminderUseCase
import com.compose.project.remindme.domain.DeleteReminderDataUseCase
import com.compose.project.remindme.domain.GetAllReminderDataUseCase
import com.compose.project.remindme.domain.InsertReminderDataUseCase
import com.compose.project.remindme.domain.ReminderDataToCategorizedUseCase
import com.compose.project.remindme.domain.ScheduleReminderUseCase
import com.compose.project.remindme.domain.model.ItemData
import com.compose.project.remindme.domain.model.NoteData
import com.compose.project.remindme.domain.model.ReminderData
import com.compose.project.remindme.presentation.common.ScreenBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReminderViewModel @Inject constructor(
    private val scheduleReminderUseCase: ScheduleReminderUseCase,
    private val insertReminderDateUseCase: InsertReminderDataUseCase,
    private val deleteReminderDataUseCase: DeleteReminderDataUseCase,
    private val getAllReminderDataUseCase: GetAllReminderDataUseCase,
    private val cancelScheduledReminderUseCase: CancelScheduledReminderUseCase,
    private val reminderDataToCategorizedUseCase: ReminderDataToCategorizedUseCase
) : ScreenBaseViewModel() {
    var reminderState: ReminderState by mutableStateOf(ReminderState())
        private set

    init {
        fetchAllData()
    }

    fun sendEvent(reminderEvent: ReminderEvent) {
        when (reminderEvent) {
            is ReminderEvent.AddButtonClickEvent -> {
                reminderState = reminderState.copy(showCreateNoteDialog = true)
            }

            is ReminderEvent.DialogCancelClickEvent -> {
                reminderState = reminderState.copy(
                    showCreateNoteDialog = false,
                    selectedReminderId = null,
                    dialogItemData = null
                )
            }

            is ReminderEvent.DialogCreateClickEvent -> {
                insertNote(
                    reminderData = DIALOG_ITEM_TO_REMINDER_DATA_MAPPER.map(
                        reminderEvent.dialogItemData
                    ).copy(id = reminderState.selectedReminderId)
                )
                reminderState = reminderState.copy(
                    showCreateNoteDialog = false,
                    selectedReminderId = null,
                    dialogItemData = null
                )
            }
            is ReminderEvent.OnLockClickEvent -> {
                reminderState = reminderState.copy(
                    lockUnlockItemId = reminderEvent.id
                )
            }
        }
    }

    override fun toggleItemLockState() {
        reminderState.categorizedItems.forEach {
            val item = it.items.find { item -> item.id == reminderState.lockUnlockItemId }
            item?.let { safeItem ->
                insertNote((safeItem as ReminderData).copy(
                    isLocked = !safeItem.isLocked
                ))
                resetLockUnlockItemId()
                return@forEach
            }
        }
    }

    override fun resetLockUnlockItemId() {
        reminderState = reminderState.copy(
            lockUnlockItemId = null
        )
    }

    private fun insertNote(reminderData: ReminderData) {
        viewModelScope.launch {
            insertReminderDateUseCase(reminderData)
            scheduleReminderUseCase(reminderData.id)
        }
    }

    override fun handleItemClickEvent(itemData: ItemData) {
        reminderState = reminderState.copy(
            dialogItemData = ITEM_DATA_TO_DIALOG_ITEM_MAPPER.map(itemData),
            showCreateNoteDialog = true,
            selectedReminderId = itemData.id
        )
    }

    override fun handleItemDeleteClickEvent(itemData: ItemData) {
        viewModelScope.launch {
            (itemData as? ReminderData)?.let {
                deleteReminderDataUseCase(it)
                cancelScheduledReminderUseCase(it)
            }
        }
    }

    private fun fetchAllData() {
        getAllReminderDataUseCase()
            .onEach {
                reminderState = reminderState.copy(
                    categorizedItems = reminderDataToCategorizedUseCase(it)
                )
            }.launchIn(viewModelScope)
    }
}