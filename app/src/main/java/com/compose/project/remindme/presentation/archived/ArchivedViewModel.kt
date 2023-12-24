package com.compose.project.remindme.presentation.archived

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.compose.project.remindme.domain.ArchivedDataToCategorizedUseCase
import com.compose.project.remindme.domain.DeleteArchivedDataUseCase
import com.compose.project.remindme.domain.GetAllArchivedDataUseCase
import com.compose.project.remindme.domain.model.ArchivedData
import com.compose.project.remindme.domain.model.ItemData
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
    private val archivedDataToCategorizedUseCase: ArchivedDataToCategorizedUseCase
) : ScreenBaseViewModel() {
    var archivedState by mutableStateOf(ArchivedState())
        private set

    init {
        fetchAllData()
    }

    fun sendEvent(archivedEvent: ArchivedEvent) {
        when (archivedEvent) {
            is ArchivedEvent.DialogCancelClickEvent -> {
//                archivedState = archivedState.copy(
//                    dialogItemData = null,
//                    showCreateNoteDialog = false,
//                    selectedNoteId = null
//                )
            }

            is ArchivedEvent.DialogCreateClickEvent -> {
//                archivedState = archivedState.copy(
//                    showCreateNoteDialog = false,
//                    dialogItemData = null,
//                    selectedNoteId = null
//                )
            }
        }
    }

    override fun handleItemClickEvent(itemData: ItemData) {

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