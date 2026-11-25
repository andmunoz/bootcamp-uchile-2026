package cl.uchile.dcc.mobile.mynotes2.data.local

import androidx.room3.Room
import androidx.room3.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import co.touchlab.kermit.Logger
import java.io.File

fun getDatabaseBuilder(): RoomDatabase.Builder<NoteDatabase> {
    val dbFile = File(System.getProperty("user.home"), "my_room.db")
    Logger.i("Database File: ${dbFile.absolutePath}", null, "getDatabaseBuilder")
    return Room.databaseBuilder<NoteDatabase>(
        name = dbFile.absolutePath,
    ).setDriver(BundledSQLiteDriver()).fallbackToDestructiveMigration(true)
}
