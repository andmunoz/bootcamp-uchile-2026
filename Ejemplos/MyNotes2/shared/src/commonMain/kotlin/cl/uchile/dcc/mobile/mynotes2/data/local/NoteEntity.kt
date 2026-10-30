package cl.uchile.dcc.mobile.mynotes2.data.local

import androidx.room3.Entity
import androidx.room3.PrimaryKey
import cl.uchile.dcc.mobile.mynotes2.data.Note

@Entity(tableName = "notes")
data class NoteEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val content: String,
    val colorHex : Long,
    val created: Long = System.currentTimeMillis()
) {
    fun toNote(): Note {
        return Note(
            id = id,
            title = title,
            content = content,
            colorHex = colorHex,
            created = created
        )
    }
}