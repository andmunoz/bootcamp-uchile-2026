@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package cl.uchile.dcc.mobile.mynotes2.data.local

import androidx.room3.RoomDatabaseConstructor
@Suppress("KotlinNoActualForExpect")
expect object NoteDatabaseConstructor :
    RoomDatabaseConstructor<NoteDatabase> {
    override fun initialize(): NoteDatabase
}