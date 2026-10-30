package cl.uchile.dcc.mobile.mynotes2.di

import androidx.room3.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import cl.uchile.dcc.mobile.mynotes2.data.NotesRepository
import cl.uchile.dcc.mobile.mynotes2.data.local.NoteDatabase
import cl.uchile.dcc.mobile.mynotes2.viewmodel.NoteViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val notesModules = module {
    // Proveemos la Base de Datos usando el Constructor de Room 3
    single {
        get<RoomDatabase.Builder<NoteDatabase>>()
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }

    // Proveemos el Repositorio inyectándole la BD automáticamente (get())
    single { NotesRepository(get()) }

    // Proveemos el ViewModel inyectándole el Repositorio (get())
    viewModelOf(::NoteViewModel)
}