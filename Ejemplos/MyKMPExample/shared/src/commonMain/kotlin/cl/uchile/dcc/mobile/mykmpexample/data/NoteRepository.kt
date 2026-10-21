package cl.uchile.dcc.mobile.mykmpexample.data

class NoteRepository {
    private val database = NoteDatabase()

    fun insertNote(note: Note) {
        database.insertNote(note)
    }

    fun getAllNotes(): List<Note> {
        return database.getAllNotes()
    }

    fun getNoteById(id: Long): Note? {
        return database.getNoteById(id)
    }
}