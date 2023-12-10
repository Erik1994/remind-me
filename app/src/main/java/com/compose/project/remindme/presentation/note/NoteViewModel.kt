package com.compose.project.remindme.presentation.note

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.compose.project.remindme.presentation.common.ScreenBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor() : ScreenBaseViewModel() {
    var noteState: NoteState by mutableStateOf(NoteState())
        private set

    fun sendEvent(noteEvent: NoteEvent) {
        when(noteEvent) {
            is NoteEvent.AddButtonClickEvent -> {
                noteState = noteState.copy(showCreateNoteDialog = true)
            }
            is NoteEvent.CancelClickEvent -> {
                noteState = noteState.copy(showCreateNoteDialog = false)
            }
        }
    }

}