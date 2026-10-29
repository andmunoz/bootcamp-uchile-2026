package cl.uchile.dcc.mobile.mynotes.data.notes

import cl.uchile.dcc.mobile.mynotes.data.notes.api.NoteAPI
import cl.uchile.dcc.mobile.mynotes.data.notes.database.Note
import cl.uchile.dcc.mobile.mynotes.data.notes.database.NoteDAO
import cl.uchile.dcc.mobile.mynotes.data.notes.database.NoteDatabase
import kotlinx.coroutines.flow.Flow

open class NoteRepository (
    private val noteDatabase: NoteDatabase,   // Fuente Local
    private val noteAPI: NoteAPI              // Fuente Remota
) {
    open fun getAllNotes(): List<NoteDAO> {
        return noteDatabase.getAllNotes()
    }

    open fun getNote(id: Long): NoteDAO? {
        return noteDatabase.getNote(id)
    }

    open suspend fun addNote(note: NoteDAO) {
        noteDatabase.addNote(note)
    }

    open suspend fun syncNotes() {
        // Sincronizar LOCAL <--> REMOTO
    }
}