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
import cl.uchile.dcc.mobile.mynotes2.ui.components.GetLocationPermission
import cl.uchile.dcc.mobile.mynotes2.ui.components.rememberCameraHandler
import cl.uchile.dcc.mobile.mynotes2.ui.screens.NoteScreen
import cl.uchile.dcc.mobile.mynotes2.viewmodel.NoteUiState
import cl.uchile.dcc.mobile.mynotes2.viewmodel.NoteViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    val viewModel = koinViewModel<NoteViewModel>()
    val uiState by viewModel.uiState.collectAsState()
    val cameraHandler = rememberCameraHandler()
    var hasLocationPermission = false
    GetLocationPermission(
        onLocationGranted = {
            hasLocationPermission = true
        }
    )

    MaterialTheme {
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
                            cameraHandler.takePicture(
                                onSuccess = { path ->
                                    viewModel.addNote(
                                        "Nueva Nota",
                                        "Contenido de prueba",
                                        hasLocationPermission,
                                        path
                                    )
                                },
                                onError = { error ->
                                    viewModel.addNote(
                                        "Nueva Nota",
                                        "Contenido de prueba",
                                        hasLocationPermission,
                                        null
                                    )
                                }
                            )
                        }
                    )
                }
            }
        }
    }
}

