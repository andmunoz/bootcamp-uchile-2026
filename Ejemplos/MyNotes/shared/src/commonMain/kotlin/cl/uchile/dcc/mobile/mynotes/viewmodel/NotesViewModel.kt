package cl.uchile.dcc.mobile.mynotes.viewmodel

import androidx.lifecycle.SavedStateHandle
import cl.uchile.dcc.mobile.mynotes.data.notes.NoteRepository

class NotesViewModel (
    private val noteRepository: NoteRepository,
    private val savedStateHandle: SavedStateHandle
) {
    // Mediar entre el modelo y la vista
}
