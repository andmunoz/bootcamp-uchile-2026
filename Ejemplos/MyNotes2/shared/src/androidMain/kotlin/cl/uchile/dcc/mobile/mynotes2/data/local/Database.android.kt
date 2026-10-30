package cl.uchile.dcc.mobile.mynotes2.data.local

import android.content.Context
import androidx.room3.Room
import androidx.room3.RoomDatabase

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<NoteDatabase> {
    return Room.databaseBuilder<NoteDatabase>(
        context = context,
        name = "my_notes.db"
    )
}