package cl.uchile.dcc.mobile.mynotes2.data

import cl.uchile.dcc.mobile.mynotes2.data.local.NoteDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NotesRepository(
    private val noteDatabase: NoteDatabase
) {
    suspend fun addNote(note: Note) {
        noteDatabase.noteDao.insertNote(note.toNoteEntity())
    }

    suspend fun removeNote(note: Note) {
        noteDatabase.noteDao.deleteNote(note.toNoteEntity())
    }

    fun getNotes(): Flow<List<Note>> {
        return noteDatabase.noteDao.getAllNotes().map { notes ->
            notes.map { it.toNote() }
        }
    }

    // SYNCRONIZE CON LA API
    fun syncNotes() {
        TODO("Not yet implemented")
        /*
            REGLAS PARA LA SINCRONIZACIÓN
            1. LO LOCAL VALE POR SOBRE LO REMOTO
            2. TRAER AL LOCAL SOLO LO NUEVO DESDE EL REMOTO
            3. SI EL LOCAL ESTÁ VACIÓ LO REMOTO VALE
         */
    }
}