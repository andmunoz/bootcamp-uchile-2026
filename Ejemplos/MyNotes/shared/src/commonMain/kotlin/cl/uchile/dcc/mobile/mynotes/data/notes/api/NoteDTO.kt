package cl.uchile.dcc.mobile.mynotes.data.notes.api

data class NoteDTO(
    val id: Long,
    val title: String,
    val content: String,
    val colorHex: Long,
    val created: Long,
)
