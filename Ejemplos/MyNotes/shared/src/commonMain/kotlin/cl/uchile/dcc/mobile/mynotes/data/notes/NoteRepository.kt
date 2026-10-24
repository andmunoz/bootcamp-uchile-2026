package cl.uchile.dcc.mobile.mynotes.data.notes

import cl.uchile.dcc.mobile.mynotes.data.notes.api.NoteAPI
import cl.uchile.dcc.mobile.mynotes.data.notes.database.NoteDAO
import cl.uchile.dcc.mobile.mynotes.data.notes.database.NoteDatabase

class NoteRepository (
    private val noteDatabase: NoteDatabase,   // Fuente Local
    private val noteAPI: NoteAPI              // Fuente Remota
) {
    fun getAllNotes(): List<NoteDAO> {
        return noteDatabase.getAllNotes()
    }

    fun getNote(id: Long): NoteDAO? {
        return noteDatabase.getNote(id)
    }

    suspend fun addNote(note: NoteDAO) {
        noteDatabase.addNote(note)
    }

    suspend fun syncNotes() {
        // Sincronizar LOCAL <--> REMOTO
    }
}