package cl.uchile.dcc.mobile.mynotes2.data.local

import androidx.room3.Room
import androidx.room3.RoomDatabase
import java.io.File

fun getDatabaseBuilder(): RoomDatabase.Builder<NoteDatabase> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "my_room.db")
    return Room.databaseBuilder<NoteDatabase>(
        name = dbFile.absolutePath,
    )
}
