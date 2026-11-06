package cl.uchile.dcc.mobile.mynotes2

import cl.uchile.dcc.mobile.mynotes2.data.Note
import cl.uchile.dcc.mobile.mynotes2.data.location.LocationData
import kotlinx.coroutines.flow.Flow
import kotlin.test.Test
import kotlin.test.assertEquals

class NoteRepositoryFake (
    private var noteDatabase: List<Note>
) {
    fun addNote(note: Note): List<Note> {
        noteDatabase = noteDatabase + note
        return noteDatabase
    }

    suspend fun removeNote(note: Note) {
        // eliminar
    }

    fun getNotes(): Flow<List<Note>>? {
        return null
        // return StateFlow(noteDatabase)
    }

    fun getNote(id: Int): Note? {
        // return noteDatabase.noteDao.getNote(id).toNote()
        return null
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

class SharedCommonTest {
    // ARRANGE
    val repository = NoteRepositoryFake(emptyList())
    val note = Note(
        id = 1,
        title = "Nueva Nota",
        content = "Nuevo contenido",
        colorHex = 1234567,
        location = LocationData(0.0, 0.0),
        picturePath = "/esta/es/la/foto.jpg",
        created = 1793922272170
    )

    @Test
    fun cuando_seAgregarUnaNota() {
        // ACT
        val newRepository = repository.addNote(note)

        // ASSERT
        assertEquals(1, newRepository.size)
    }
}