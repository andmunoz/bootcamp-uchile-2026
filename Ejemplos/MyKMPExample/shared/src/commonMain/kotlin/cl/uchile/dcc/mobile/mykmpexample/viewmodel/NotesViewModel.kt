package cl.uchile.dcc.mobile.mykmpexample.viewmodel

import androidx.lifecycle.ViewModel
import cl.uchile.dcc.mobile.mykmpexample.data.Note
import cl.uchile.dcc.mobile.mykmpexample.data.NoteRepository

class NotesViewModel(
    private val noteRepository: NoteRepository
) : ViewModel() {
    fun addNote(note: Note) {
        noteRepository.insertNote(note)
    }
}
