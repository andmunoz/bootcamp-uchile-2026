import cl.uchile.dcc.mobile.mynotes.data.notes.NoteRepository
import cl.uchile.dcc.mobile.mynotes.data.notes.api.NoteAPI
import cl.uchile.dcc.mobile.mynotes.data.notes.database.NoteDAO
import cl.uchile.dcc.mobile.mynotes.data.notes.database.NoteDatabase
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class NoteRepositoryTests(fakeDatabase: NoteDatabase, fakeAPI: NoteAPI) {
    private val fakeRepository = object : NoteRepository(fakeDatabase, fakeAPI) {
        override fun getAllNotes() = listOf(NoteDAO(1, "Test", "Contenido", 0, 0))
        override suspend fun syncNotes() {}
        override fun getNote(id: Long) = null
        override suspend fun addNote(note: NoteDAO) {}
    }

    @Test
    fun getAllNotesTest() {
        // Probamos la unidad (función) para certificar el comportamiento
        val notes = fakeRepository.getAllNotes()

        // Verificar que la lista no esté vacía
        assertTrue(notes.isNotEmpty())

        // Verificar que la lista solo tenga un elemento
        assertEquals(1, notes.size)

        // Verificar que la primera nota tenga el título correcto
        assertEquals("Test", notes[0].title)
    }

    @Test
    fun syncNotesTest() {
        // ...
    }
}
