package cl.uchile.dcc.mobile.mynotes.data.notes.database

class NoteDatabase(
    private val driverFactory: DriverFactory
) {
    private val nodeDB = NoteDB(driverFactory.createDriver())

    fun getAllNotes(): List<Note> {
        return nodeDB.noteQueries.selectAll().executeAsList()
    }

    fun getNote(id: Long): Note? {
        return nodeDB.noteQueries.selectNoteById(id).executeAsOneOrNull()
    }

    suspend fun addNote(note: Note) {
        nodeDB.noteQueries
            .insertNote(note.title, note.content, note.colorHex, note.created)
    }
}