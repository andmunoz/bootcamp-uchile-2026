package cl.uchile.dcc.mobile.mynotes.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import cl.uchile.dcc.mobile.mynotes.data.notes.NoteRepository
import cl.uchile.dcc.mobile.mynotes.data.notes.api.NoteAPI
import cl.uchile.dcc.mobile.mynotes.data.notes.api.createHttpClient
import cl.uchile.dcc.mobile.mynotes.data.notes.database.NoteDatabase
import cl.uchile.dcc.mobile.mynotes.data.notes.database.DriverFactory

@Composable
fun App(
    driverFactory: DriverFactory,
) {
    val httpClient = createHttpClient(NoteAPI.baseUrl)
    val repository = NoteRepository(
        noteDatabase = NoteDatabase(driverFactory = driverFactory),
        noteAPI = NoteAPI(client = httpClient)
    )
    MaterialTheme {
        MainScreen(repository)
    }
}

@Composable
expect fun MainScreen(repository: NoteRepository)