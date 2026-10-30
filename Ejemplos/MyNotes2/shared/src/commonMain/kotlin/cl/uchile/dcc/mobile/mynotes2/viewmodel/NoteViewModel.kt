package cl.uchile.dcc.mobile.mynotes2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.uchile.dcc.mobile.mynotes2.data.Note
import cl.uchile.dcc.mobile.mynotes2.data.NotesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NoteViewModel(
    private val notesRepository: NotesRepository
): ViewModel() {
    val uiState: StateFlow<NoteUiState> = notesRepository.getNotes()
        .map { note -> NoteUiState.Success(note) as NoteUiState }
        .catch { error -> emit(NoteUiState.Error(error.message ?: "Error desconocido")) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = NoteUiState.Loading
        )

    fun addNote(title: String, content: String) {
        viewModelScope.launch {
            val note = Note(
                title = title,
                content = content,
                colorHex = 0L
            )
            notesRepository.addNote(note)
        }
    }

    fun updateNote(id: Int, title: String, content: String, colorHex: Long, created: Long) {
        viewModelScope.launch {
            val note = Note(
                id = id,
                title = title,
                content = content,
                colorHex = colorHex,
                created = created
            )
            notesRepository.addNote(note)
        }
    }

    fun removeNote(note: Note) {
        viewModelScope.launch {
            notesRepository.removeNote(note)
        }
    }
}