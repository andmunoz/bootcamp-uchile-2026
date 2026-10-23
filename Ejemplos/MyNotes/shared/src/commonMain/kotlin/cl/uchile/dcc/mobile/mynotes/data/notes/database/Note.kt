package cl.uchile.dcc.mobile.mynotes.data.notes.database

data class Note(
    val id: Long?,
    val title: String,
    val content: String,
    val colorHex: Long,
    val created: Long,
)
