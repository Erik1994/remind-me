package com.compose.project.remindme.presentation.archived

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.compose.project.remindme.domain.ArchivedDataToCategorizedUseCase
import com.compose.project.remindme.domain.DeleteArchivedDataUseCase
import com.compose.project.remindme.domain.GetAllArchivedDataUseCase
import com.compose.project.remindme.domain.InsertArchivedDataUseCase
import com.compose.project.remindme.domain.model.ArchivedData
import com.compose.project.remindme.domain.model.ItemData
import com.compose.project.remindme.domain.model.ReminderData
import com.compose.project.remindme.presentation.common.ScreenBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArchivedViewModel @Inject constructor(
    private val getAllArchivedDataUseCase: GetAllArchivedDataUseCase,
    private val deleteArchivedDataUseCase: DeleteArchivedDataUseCase,
    private val insertArchivedDataUseCase: InsertArchivedDataUseCase,
    private val archivedDataToCategorizedUseCase: ArchivedDataToCategorizedUseCase
) : ScreenBaseViewModel() {
    var archivedState by mutableStateOf(ArchivedState())
        private set

    init {
        fetchAllData()
    }

    fun sendEvent(archivedEvent: ArchivedEvent) {
        when (archivedEvent) {
            is ArchivedEvent.OnLockClickEvent -> {
                archivedState = archivedState.copy(
                    lockUnlockItemId = archivedEvent.id
                )
            }
            else -> Unit
        }
    }

    override fun handleItemClickEvent(itemData: ItemData) {

    }

    override fun toggleItemLockState() {
        archivedState.categorizedItems.forEach {
            val item = it.items.find { item -> item.id == archivedState.lockUnlockItemId }
            item?.let { safeItem ->
                insertArchivedItem((safeItem as ArchivedData).copy(
                    isLocked = !safeItem.isLocked
                ))
                resetLockUnlockItemId()
                return@forEach
            }
        }
    }

    override fun resetLockUnlockItemId() {
        archivedState = archivedState.copy(
            lockUnlockItemId = null
        )
    }

    private fun insertArchivedItem(archivedData: ArchivedData) {
        viewModelScope.launch {
            insertArchivedDataUseCase(archivedData)
            fetchAllData()
        }
    }

    override fun handleItemDeleteClickEvent(itemData: ItemData) {
        viewModelScope.launch {
            (itemData as? ArchivedData)?.let {
                deleteArchivedDataUseCase(it)
            }
        }
    }

    private fun fetchAllData() {
        getAllArchivedDataUseCase()
            .onEach {
                archivedState = archivedState.copy(
                    categorizedItems = archivedDataToCategorizedUseCase(it)
                )
            }.launchIn(viewModelScope)
    }
}