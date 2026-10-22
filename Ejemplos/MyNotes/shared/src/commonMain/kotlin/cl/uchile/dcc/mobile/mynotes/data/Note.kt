package cl.uchile.dcc.mobile.mynotes.data

data class Note(
    val id: Long?,
    val title: String,
    val content: String,
    val colorHex: Long,
    val created: Long,
)
