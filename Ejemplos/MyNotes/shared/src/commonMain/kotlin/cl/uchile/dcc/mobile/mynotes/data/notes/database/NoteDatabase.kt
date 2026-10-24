package cl.uchile.dcc.mobile.mynotes.data.notes.database

class NoteDatabase(
    private val driverFactory: DriverFactory
) {
    private val nodeDB = NoteDB(driverFactory.createDriver())

    fun getAllNotes(): List<NoteDAO> {
        return nodeDB
            .noteQueries
            .selectAll()
            .executeAsList()
            .map {
                NoteDAO(
                    id = it.id,
                    title = it.title,
                    content = it.content,
                    colorHex = it.colorHex,
                    created = it.created
                )
            }
    }

    fun getNote(id: Long): NoteDAO? {
        return nodeDB
            .noteQueries
            .selectNoteById(id)
            .executeAsOneOrNull()
            ?.let {
                NoteDAO(
                    id = it.id,
                    title = it.title,
                    content = it.content,
                    colorHex = it.colorHex,
                    created = it.created
                )
            }
    }

    suspend fun addNote(note: NoteDAO) {
        nodeDB.noteQueries
            .insertNote(note.title, note.content, note.colorHex, note.created)
    }
}