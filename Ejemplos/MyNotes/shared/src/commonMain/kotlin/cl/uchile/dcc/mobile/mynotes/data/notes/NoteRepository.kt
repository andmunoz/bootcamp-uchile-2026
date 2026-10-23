package cl.uchile.dcc.mobile.mynotes.data.notes

import cl.uchile.dcc.mobile.mynotes.data.notes.api.NoteAPI
import cl.uchile.dcc.mobile.mynotes.data.notes.database.Note
import cl.uchile.dcc.mobile.mynotes.data.notes.database.NoteDatabase

class NoteRepository (
    private val noteDatabase: NoteDatabase,   // Fuente Local
    private val noteAPI: NoteAPI              // Fuente Remota
) {
    fun getAllNotes(): List<Note> {
        return noteDatabase.getAllNotes()
    }

    fun getNote(id: Long): Note? {
        return noteDatabase.getNote(id)
    }

    fun addNote(note: Note) {
        noteDatabase.addNote(note)
    }

    suspend fun syncNotes() {
        // Sincronizar LOCAL <--> REMOTO
    }
}