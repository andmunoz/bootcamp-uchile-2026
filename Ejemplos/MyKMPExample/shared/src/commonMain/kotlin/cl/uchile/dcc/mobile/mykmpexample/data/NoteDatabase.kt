package cl.uchile.dcc.mobile.mykmpexample.data

class NoteDatabase {
    private val notes = mutableListOf<Note>()

    fun insertNote(note: Note) {
        val count = notes.size
        notes.add(note.copy(id = count.toLong() + 1))
    }

    fun getAllNotes(): List<Note> {
        return notes
    }

    fun getNoteById(id: Long): Note? {
        return notes.find { it.id == id }
    }
}