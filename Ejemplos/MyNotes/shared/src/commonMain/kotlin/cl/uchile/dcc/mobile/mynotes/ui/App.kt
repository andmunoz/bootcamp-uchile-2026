package cl.uchile.dcc.mobile.mynotes.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.uchile.dcc.mobile.mynotes.data.notes.NoteRepository
import cl.uchile.dcc.mobile.mynotes.data.notes.api.NoteAPI
import cl.uchile.dcc.mobile.mynotes.data.notes.api.createHttpClient
import cl.uchile.dcc.mobile.mynotes.data.notes.database.DriverFactory
import cl.uchile.dcc.mobile.mynotes.data.notes.database.NoteDatabase
import cl.uchile.dcc.mobile.mynotes.di.appModule
import cl.uchile.dcc.mobile.mynotes.viewmodel.NotesViewModel

@Composable
fun App(driverFactory: DriverFactory) {
    MaterialTheme {
        val httpClient = createHttpClient(NoteAPI.baseUrl)
        val repository = NoteRepository(
            noteDatabase = NoteDatabase(driverFactory = driverFactory),
            noteAPI = NoteAPI(client = httpClient)
        )
        BoxWithConstraints {
            when (windowSizeClass.widthSizeClass) {
                WindowWidthSizeClass.Compact -> {
                    // ...
                }
                WindowWidthSizeClass.Medium -> {
                    // ...
                }
                WindowWidthSizeClass.Expanded -> {
                    // ...
                }
            }
            if (maxWidth < 600.dp) { // Móvil
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    NoteListComponent(repository)
                }
            } else { // Tablet o Desktop
                Row (
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier.weight(0.3f)
                    ) {
                        NoteListComponent(repository)
                    }
                    Box(
                        modifier = Modifier.weight(0.7f)
                    ) {
                        NoteDetailComponent(repository)
                    }
                }
            }
        }
    }
}

@Composable
fun NoteDetailComponent(repository: NoteRepository, noteId: Long? = null) {
    TODO("Not yet implemented")
}

@Composable
fun NoteListComponent(repository: NoteRepository) {
    TODO("Not yet implemented")
}

@Composable
expect fun MainScreen(repository: NoteRepository)