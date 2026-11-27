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
    val viewModel = koinViewModel<NoteViewModel>()

    // Definimos una variable para manejar el estado de la app al abrir
    val uiState by viewModel.uiState.collectAsState()

    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            when (val state = uiState) {
                is NoteUiState.Loading -> {
                    // Si aún estamos "cargando" mostramos un indicador de progreso
                    Box(
                        Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is NoteUiState.Error -> {
                    // Si ocurrió un error al iniciar, mostramos que hay un error
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
                    // Si al iniciar no ocurre nada, entonces pintamos la pantalla principal
                    NoteScreen(
                        notes = state.notes,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

