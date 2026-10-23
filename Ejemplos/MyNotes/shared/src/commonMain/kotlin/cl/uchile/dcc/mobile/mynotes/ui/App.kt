package cl.uchile.dcc.mobile.mynotes.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import cl.uchile.dcc.mobile.mynotes.data.notes.NoteRepository
import cl.uchile.dcc.mobile.mynotes.data.notes.api.NoteAPI
import cl.uchile.dcc.mobile.mynotes.data.notes.api.createHttpClient
import cl.uchile.dcc.mobile.mynotes.data.notes.database.NoteDatabase

@Composable
fun App(
    repository: NoteRepository = NoteRepository(
        noteDatabase = NoteDatabase(),
        noteAPI = NoteAPI(
            client = createHttpClient(NoteAPI.baseUrl)
        )
    )
) {
    MaterialTheme {
        MainScreen(repository)
    }
}

@Composable
expect fun MainScreen(repository: NoteRepository)