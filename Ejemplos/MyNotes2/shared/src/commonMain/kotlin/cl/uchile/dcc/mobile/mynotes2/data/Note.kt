package cl.uchile.dcc.mobile.mynotes2.data

import cl.uchile.dcc.mobile.mynotes2.data.local.NoteEntity
import cl.uchile.dcc.mobile.mynotes2.data.location.LocationData

data class Note(
    val id: Int = 0,
    val title: String,
    val content: String,
    val colorHex: Long,
    val location: LocationData?,
    val picturePath: String?,
    val created: Long = System.currentTimeMillis()
) {
    fun toNoteEntity(): NoteEntity {
        return NoteEntity(
            id = id,
            title = title,
            content = content,
            colorHex = colorHex,
            latitude = location?.latitude,
            longitude = location?.longitude,
            picturePath = picturePath,
            created = created
        )
    }
}
