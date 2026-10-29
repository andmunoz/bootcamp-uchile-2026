package cl.uchile.dcc.mobile.mynotes.di

import cl.uchile.dcc.mobile.mynotes.data.notes.NoteRepository
import cl.uchile.dcc.mobile.mynotes.data.notes.api.createHttpClient
import cl.uchile.dcc.mobile.mynotes.viewmodel.NotesViewModel
import org.koin.dsl.module

val appModule = module {
    // single: Instancia únicas (SINGLETON)
    single {
        createHttpClient(get())
    }
    single {
        NoteRepository(get(), get())
    }

    // factory: Instancias diferentes (NUEVAS)
    factory {
        NotesViewModel(get(), get())
    }
}
