package cl.uchile.dcc.mobile.mynotes2.data.local

import androidx.room3.ConstructedBy
import androidx.room3.Database
import androidx.room3.RoomDatabase

@Database(entities = [NoteEntity::class], version = 3)
@ConstructedBy(NoteDatabaseConstructor::class)
abstract class NoteDatabase: RoomDatabase() {
    abstract val noteDao: NoteDao
}