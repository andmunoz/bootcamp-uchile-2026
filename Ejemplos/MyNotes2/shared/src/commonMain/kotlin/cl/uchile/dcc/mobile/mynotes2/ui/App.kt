package cl.uchile.dcc.mobile.mynotes2.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cl.uchile.dcc.mobile.mynotes2.ui.screens.NoteScreen
import cl.uchile.dcc.mobile.mynotes2.viewmodel.NoteUiState
import cl.uchile.dcc.mobile.mynotes2.viewmodel.NoteViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    // 1. Inyectamos el ViewModel
    val viewModel = koinViewModel<NoteViewModel>()

    // 2. Observamos el estado
    val uiState by viewModel.uiState.collectAsState()

    MaterialTheme {
        // 3. Pintamos la UI dependiendo del estado
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            when (val state = uiState) {
                is NoteUiState.Loading -> {
                    Box(
                        Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is NoteUiState.Error -> {
                    Box(
                        Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Error: ${state.message}",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }

                is NoteUiState.Success -> {
                    NoteScreen(
                        notes = state.notes,
                        onAddNote = {
                            viewModel.addNote(
                                "Nueva Nota",
                                "Contenido de prueba"
                            )
                        }
                    )
                }
            }
        }
    }
}

