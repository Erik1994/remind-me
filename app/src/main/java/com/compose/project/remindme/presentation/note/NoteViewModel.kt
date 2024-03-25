package com.compose.project.remindme.presentation.note

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.compose.project.remindme.data.mapper.DIALOG_ITEM_TO_NOTE_DATA_MAPPER
import com.compose.project.remindme.data.mapper.ITEM_DATA_TO_DIALOG_ITEM_MAPPER
import com.compose.project.remindme.domain.DeleteNoteDataUseCase
import com.compose.project.remindme.domain.GetAllNoteDataUseCase
import com.compose.project.remindme.domain.InsertNoteDataUseCase
import com.compose.project.remindme.domain.NoteDataToCategorizedUseCase
import com.compose.project.remindme.domain.model.ItemData
import com.compose.project.remindme.domain.model.NoteData
import com.compose.project.remindme.presentation.common.ScreenBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val insertNoteDataUseCase: InsertNoteDataUseCase,
    private val deleteNoteDataUseCase: DeleteNoteDataUseCase,
    private val getAllNoteDataUseCase: GetAllNoteDataUseCase,
    private val noteDataToCategorizedUseCase: NoteDataToCategorizedUseCase
) : ScreenBaseViewModel() {
    var noteState: NoteState by mutableStateOf(NoteState())
        private set

    init {
        fetchAllData()
    }

    fun sendEvent(noteEvent: NoteEvent) {
        when (noteEvent) {
            is NoteEvent.AddButtonClickEvent -> {
                noteState = noteState.copy(showCreateNoteDialog = true)
            }

            is NoteEvent.DialogCancelClickEvent -> {
                noteState = noteState.copy(
                    dialogItemData = null,
                    showCreateNoteDialog = false,
                    selectedNoteId = null
                )
            }

            is NoteEvent.DialogCreateClickEvent -> {
                insertNote(
                    DIALOG_ITEM_TO_NOTE_DATA_MAPPER.map(
                        noteEvent.dialogItemData
                    ).copy(id = noteState.selectedNoteId)
                )
                noteState = noteState.copy(
                    showCreateNoteDialog = false,
                    dialogItemData = null,
                    selectedNoteId = null
                )
            }

            is NoteEvent.OnLockClickEvent -> {
                noteState = noteState.copy(
                    lockUnlockItemId = noteEvent.id
                )
            }
        }
    }

    override fun toggleItemLockState() {
        noteState.categorizedItems.forEach {
            val item = it.items.find { item -> item.id == noteState.lockUnlockItemId }
            item?.let { safeItem ->
                insertNote((safeItem as NoteData).copy(
                    isLocked = !safeItem.isLocked
                ))
                resetLockUnlockItemId()
                return@forEach
            }
        }
    }


    override fun resetLockUnlockItemId() {
        noteState = noteState.copy(
            lockUnlockItemId = null
        )
    }
    override fun handleItemClickEvent(itemData: ItemData) {
        noteState = noteState.copy(
            dialogItemData = ITEM_DATA_TO_DIALOG_ITEM_MAPPER.map(itemData),
            showCreateNoteDialog = true,
            selectedNoteId = itemData.id
        )
    }

    override fun handleItemDeleteClickEvent(itemData: ItemData) {
        (itemData as? NoteData)?.let {
            deleteNote(it)
        }
    }

    private fun insertNote(noteData: NoteData) {
        viewModelScope.launch {
            insertNoteDataUseCase(noteData)
            fetchAllData()
        }
    }

    private fun deleteNote(noteData: NoteData) {
        viewModelScope.launch {
            deleteNoteDataUseCase(noteData)
            fetchAllData()
        }
    }

    private fun fetchAllData() {
        getAllNoteDataUseCase()
            .onEach {
                noteState = noteState.copy(
                    categorizedItems = noteDataToCategorizedUseCase(it)
                )
            }.launchIn(viewModelScope)
    }
}