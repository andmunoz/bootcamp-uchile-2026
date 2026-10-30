package cl.uchile.dcc.mobile.mynotes2.viewmodel

import cl.uchile.dcc.mobile.mynotes2.data.Note

sealed interface NoteUiState {
    object Loading: NoteUiState
    data class Success(val notes: List<Note>): NoteUiState
    data class Error(val message: String): NoteUiState
}