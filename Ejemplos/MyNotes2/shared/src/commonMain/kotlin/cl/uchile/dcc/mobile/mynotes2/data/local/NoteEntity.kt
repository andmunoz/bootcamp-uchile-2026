package cl.uchile.dcc.mobile.mynotes2.data.local

import androidx.room3.Entity
import androidx.room3.PrimaryKey
import cl.uchile.dcc.mobile.mynotes2.data.Note
import cl.uchile.dcc.mobile.mynotes2.data.location.LocationData
import javax.xml.stream.Location

@Entity(tableName = "notes")
data class NoteEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val content: String,
    val colorHex: Long,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val picturePath: String? = null,
    val created: Long = System.currentTimeMillis()
) {
    fun toNote(): Note {
        var location: LocationData? = null
        if (latitude != null && longitude != null) {
            location = LocationData(latitude, longitude)
        }
        return Note(
            id = id,
            title = title,
            content = content,
            colorHex = colorHex,
            location = location,
            picturePath = picturePath,
            created = created
        )
    }
}