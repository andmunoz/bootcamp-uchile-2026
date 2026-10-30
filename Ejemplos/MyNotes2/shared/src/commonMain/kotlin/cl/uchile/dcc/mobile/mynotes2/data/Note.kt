package cl.uchile.dcc.mobile.mynotes2.data

import cl.uchile.dcc.mobile.mynotes2.data.local.NoteEntity

data class Note(
    val id: Int = 0,
    val title: String,
    val content: String,
    val colorHex : Long,
    val created: Long = System.currentTimeMillis()
) {
    fun toNoteEntity(): NoteEntity {
        return NoteEntity(
            id = id,
            title = title,
            content = content,
            colorHex = colorHex,
            created = created
        )
    }
}
